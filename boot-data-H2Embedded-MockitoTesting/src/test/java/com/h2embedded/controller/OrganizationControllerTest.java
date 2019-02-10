package com.h2embedded.controller;

import com.h2embedded.Application;
import com.h2embedded.entity.Organization;
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
 * Implement tests for OrganizationController
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Spy
    @InjectMocks
    private OrganizationController organizationController;

    @Test
    public void addOrganization() throws Exception {
        Organization organization = new Organization();
        organization.setId(4L);
        organization.setName("four");

        mvc.perform(post("/orgs/orgs")
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.asJsonString(organization)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.name", is("four")));
    }

    @Test
    public void updateOrganization() throws Exception {
        Organization organization = new Organization();
        organization.setId(3L);
        organization.setName("three-1");

        mvc.perform(put("/orgs/3")
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.asJsonString(organization)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("three-1")));
    }

    @Test
    public void getOrganizationById() throws Exception {
        mvc.perform(get("/orgs/2")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("second")));
    }

    @Test
    public void deleteOrganization() throws Exception {
        mvc.perform(delete("/orgs/4")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllOrganization() throws Exception {
        mvc.perform(get("/orgs/orgs")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].name", is("second")));
    }

}