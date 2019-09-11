import com.example.controller.UserController;
import com.example.controller.UserControllerImpl;
import com.example.model.User;
import com.example.service.UserServiceImpl;
import com.example.validator.UserValidator;
import com.example.validator.UsernameLengthValidator;
import com.example.validator.UsernameWhitespaceValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<UserValidator> userValidators = new ArrayList<>();
        userValidators.add(new UsernameLengthValidator());
        userValidators.add(new UsernameWhitespaceValidator());
        UserController userController = new UserControllerImpl(new UserServiceImpl(), userValidators);

        userController.save(new User("validnév"));
        userController.save(new User("rövid"));
        userController.save(new User(null));
        userController.save(new User("szóköz is"));
        userController.save(null);
    }
}
