package com.h2embedded.service.org.impl;

import com.h2embedded.Application;
import com.h2embedded.entity.Organization;
import com.h2embedded.service.org.OrganizationService;
import org.assertj.core.api.AssertionsForClassTypes;
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
public class OrganizationServiceImplTest {

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void save() {
        Organization organization = new Organization();
        organization.setId(0L);
        organization.setName("four");

        Organization org = organizationService.save(organization);

        assertThat(org.getId()).isEqualTo(5L);
        assertThat(org.getName()).isEqualTo("four");
    }

    @Test
    public void updateById() {
        Organization orgNewData = new Organization();
        orgNewData.setId(2L);
        orgNewData.setName("second-1");

        Organization org = organizationService.updateById(2L, orgNewData);

        assertThat(org.getId()).isEqualTo(orgNewData.getId());
        assertThat(org.getName()).isEqualTo(orgNewData.getName());
    }

    @Test
    public void findById() {
        Organization organization = organizationService.findById(2L);
        assertThat(organization.getId()).isEqualTo(2L);
        assertThat(organization.getName()).isEqualTo("second");
    }

    @Test
    public void getAll() {
        List<Organization> lOrganization = organizationService.getAll();

        assertThat(lOrganization.size()).isEqualTo(4);
        AssertionsForClassTypes.assertThat(lOrganization.get(1).getId()).isEqualTo(2L);
        AssertionsForClassTypes.assertThat(lOrganization.get(1).getName()).isEqualTo("second");
    }

}