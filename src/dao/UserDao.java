package dao;

import domain.User;
import javafx.scene.control.TextField;

public interface UserDao {
    User findUser(String username,String password);


    User findByUserName(String usernameText);

    void updateUser(String username, String  password);
}
