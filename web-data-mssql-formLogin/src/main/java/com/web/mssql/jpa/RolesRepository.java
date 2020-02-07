package com.web.mssql.jpa;

import com.web.mssql.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com> on 16.12.2018 15:20
 */

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
