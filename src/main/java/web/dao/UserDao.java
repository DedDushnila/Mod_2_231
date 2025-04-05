package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    void addUser(User user);
    void updateUser(Long id, String name, String lastName, Integer age);
    void deleteUser(Long id);

}