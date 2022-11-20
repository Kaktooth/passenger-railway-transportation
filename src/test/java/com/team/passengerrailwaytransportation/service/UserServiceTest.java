package com.team.passengerrailwaytransportation.service;

import com.team.passengerrailwaytransportation.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void shouldReturnCorrectAllUser() {
        User user = createUser();
        List<User> list = userService.findAll();
        Assertions.assertTrue(list.contains(user));
    }

    private User createUser() {
        User user = new User();
        return userService.save(user);
    }
}
