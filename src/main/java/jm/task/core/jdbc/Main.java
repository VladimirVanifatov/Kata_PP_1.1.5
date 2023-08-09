package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 10);
        userService.saveUser("Petr", "Petrov", (byte) 20);
        userService.saveUser("Vladimir", "Vanifatov", (byte) 30);
        userService.saveUser("Ivan", "Sidorov", (byte) 40);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
