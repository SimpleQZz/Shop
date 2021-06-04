package dao;

import domain.User;

import java.util.List;

public interface UserDao {
    User findUser(String username,String password);


    User findByUserName(String usernameText);

    void updateUser(String username, String  password);

    List<User> findAll();

    void save(User user);
}
