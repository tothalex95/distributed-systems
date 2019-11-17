package com.example.hexa;

import com.jcraft.jsch.ChannelSftp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.util.Arrays;

@Configuration
@EnableIntegration
public class BasicIntegrationConfig {

    private static final String AuthorInputDirectory = "authors";
    private static final String FilePattern = "*.json";

    @Value("${sftp.username}")
    private String sftpUsername;

    @Value("${sftp.password}")
    private String sftpPassword;

    @Bean
    public MessageChannel authorFileNameChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel authorChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel bookSftpChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel bookChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(channel = "authorFileNameChannel",
            poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
        fileReadingMessageSource.setDirectory(new File(AuthorInputDirectory));
        CompositeFileListFilter compositeFileListFilter = new CompositeFileListFilter();
        compositeFileListFilter.addFilter(new SimplePatternFileListFilter(FilePattern));
        compositeFileListFilter.addFilter(new AcceptOnceFileListFilter(10));
        /*fileReadingMessageSource.setFilter(
                new CompositeFileListFilter<>(
                    Arrays.asList(
                        new SimplePatternFileListFilter(FilePattern),
                        new AcceptOnceFileListFilter(10)
                    )
                )
        );*/
        return fileReadingMessageSource;
    }

    @Bean
    public SessionFactory<ChannelSftp.LsEntry> sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
        factory.setHost("jerry.iit.uni-miskolc.hu");
        factory.setPort(22);
        factory.setUser(sftpUsername);
        factory.setPassword(sftpPassword);
        factory.setAllowUnknownKeys(true);
        return new CachingSessionFactory<>(factory);
    }

    @Bean
    public SftpInboundFileSynchronizer sftpInboundFileSynchronizer() {
        SftpInboundFileSynchronizer fileSynchronizer = new SftpInboundFileSynchronizer(sftpSessionFactory());
        fileSynchronizer.setDeleteRemoteFiles(true);
        fileSynchronizer.setRemoteDirectory("books");
        fileSynchronizer.setFilter(new SftpSimplePatternFileListFilter("*.json"));
        return fileSynchronizer;
    }

    @Bean
    @InboundChannelAdapter(channel = "bookSftpChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> sftpMessageSource() {
        SftpInboundFileSynchronizingMessageSource sftpMessageSource = new SftpInboundFileSynchronizingMessageSource(sftpInboundFileSynchronizer());
        sftpMessageSource.setLocalDirectory(new File("books"));
        sftpMessageSource.setAutoCreateLocalDirectory(true);
        sftpMessageSource.setLocalFilter(new AcceptOnceFileListFilter<>());
        sftpMessageSource.setMaxFetchSize(1);
        return sftpMessageSource;
    }

}
