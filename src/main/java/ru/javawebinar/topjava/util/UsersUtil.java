package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> users = Arrays.asList(
            new User(null, "User", "user@gmail.com", "qwerty", Role.USER),
            new User(null, "Admin", "admin@gmail.com", "qwerty", Role.ADMIN)
    );
}
