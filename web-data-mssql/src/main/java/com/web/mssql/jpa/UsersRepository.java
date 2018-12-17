package com.web.mssql.jpa;

import com.web.mssql.entity.Users;
import com.web.mssql.entity.projection.VwUserWithRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com> on 16.12.2018 14:57
 */

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value = "select uID, name, pass, active, uDate, rID, authority, rDate from webdb.dbo.vwUserWithRole where name = :name and pass = :pass", nativeQuery = true)
    public VwUserWithRole findActiveUserForLogin(@Param("name") String name, @Param("pass") String pass);

}
