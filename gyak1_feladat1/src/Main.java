import com.example.controller.UserController;
import com.example.controller.UserControllerImpl;
import com.example.model.User;
import com.example.validator.UserValidatorImpl;

public class Main {

    public static void main(String[] args) {
        UserController userController = new UserControllerImpl(new UserValidatorImpl());

        userController.save(new User("validnév"));
        userController.save(new User("rövid"));
        userController.save(new User(null));
        userController.save(new User("szóköz is"));
        userController.save(null);
    }
}
