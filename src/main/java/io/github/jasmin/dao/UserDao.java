package io.github.jasmin.dao;

import io.github.jasmin.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    public static List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User(1,"hyunsoo","mhs","1234"));
        users.add(new User(2,"jungtae","pjt","1234"));
        users.add(new User(3,"seongjae","lsj","1234"));
        users.add(new User(4,"sangbum","lsb","1234"));
        users.add(new User(5,"areum","jar","1234"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserByUserId(String userId) {
        return users
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny()
                .orElse(new User(-1, "", "", ""));
    }

    public User insertUser(User user) {
        users.add(user);

        return user;
    }

    public void updateUser(String userId,User user) {
        users.stream()
                .filter(curUser -> curUser.getUserId().equals(userId))
                .findAny()
                .orElse(new User(-1, "", "", ""))
                .setUserName(user.getUserName());
    }

    public void deleteUser(String userId) {
        users.removeIf(user -> user.getUserId().equals(userId));
    }
}
