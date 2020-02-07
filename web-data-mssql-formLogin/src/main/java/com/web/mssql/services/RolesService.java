package com.web.mssql.services;

import com.web.mssql.entity.Roles;
import com.web.mssql.jpa.RolesRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com> on 17.12.2018 12:59
 */
@Service
@Scope(value = "singleton")
public class RolesService implements InitializingBean {

    @Autowired
    private RolesRepository rolesRepository;

    private List<Roles> rolesList;

    @Override
    public void afterPropertiesSet() throws Exception {
        rolesList = rolesRepository.findAll();
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public synchronized void addToRolesSet(Roles roles) {
        rolesList.add(roles);
    }

    public Roles findRoles(Integer id) {
        return rolesList.stream()
                .filter(rolesStream -> rolesStream.getId() == id)
                .findFirst().orElse(null);
    }
}
