package com.h2embedded.controller;

import com.h2embedded.Application;
import com.h2embedded.entity.User;
import com.h2embedded.service.user.UserService;
import com.h2embedded.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Implement tests for UserController
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Spy
    @InjectMocks
    private UserController userController;

    @Spy
    private UserService userService;

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setId(5L);
        user.setUsername("new");
        user.setPassword("new");

        mvc.perform(post("/orgs/3/users")
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.asJsonString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.username", is("new")));
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setId(2L);
        user.setUsername("two-1");
        user.setPassword("two-1");

        mvc.perform(put("/orgs/1/users/2")
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("two-1")));
    }

    @Test
    public void getUser() throws Exception {
        mvc.perform(get("/orgs/1/users/2")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("two-1")));
    }

    @Test
    public void deleteUser() throws Exception {
        mvc.perform(delete("/orgs/3/users/4")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllByOrganization() throws Exception {
        mvc.perform(get("/orgs/1/users")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].username", is("two-1")));
    }

}