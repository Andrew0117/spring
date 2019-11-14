package com.h2embedded.service.user.impl;

import com.h2embedded.Application;
import com.h2embedded.entity.User;
import com.h2embedded.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Implement tests for UserServiceImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        User userNewData = new User();
        userNewData.setId(0L);
        userNewData.setUsername("five");
        userNewData.setPassword("five");

        User user = userService.save(3L, userNewData);

        assertThat(user.getId()).isEqualTo(5L);
        assertThat(user.getUsername()).isEqualTo("five");
    }

    @Test
    public void updateUser() {
        User userNewData = new User();
        userNewData.setId(2L);
        userNewData.setUsername("two-1");
        userNewData.setPassword("two-1");

        User user = userService.updateUser(1L, 2L, userNewData);

        assertThat(user.getId()).isEqualTo(userNewData.getId());
        assertThat(user.getUsername()).isEqualTo(userNewData.getUsername());
    }

    @Test
    public void findUser() {
        User user = userService.findUser(1L, 2L);
        assertThat(user.getId()).isEqualTo(2L);
        assertThat(user.getUsername()).isEqualTo("two");
    }

    @Test
    public void getAllByOrganization() {
        List<User> lUser = userService.getAllByOrganization(1L);

        assertThat(lUser.size()).isEqualTo(2);
        assertThat(lUser.get(0).getId()).isEqualTo(1L);
        assertThat(lUser.get(0).getUsername()).isEqualTo("one");
        assertThat(lUser.get(1).getId()).isEqualTo(2L);
        assertThat(lUser.get(1).getUsername()).isEqualTo("two-1");
    }

}