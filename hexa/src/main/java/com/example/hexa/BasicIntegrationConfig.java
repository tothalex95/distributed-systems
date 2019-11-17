package com.example.hexa;

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
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
@EnableIntegration
public class BasicIntegrationConfig {

    private static final String AuthorInputDirectory = "authors";
    private static final String FilePattern = "*.json";

    @Bean
    public MessageChannel authorFileNameChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel authorChannel() {
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

}
