package org.service.user.repositories;

import org.service.user.entity.Users;
import org.service.user.entity.projection.VwUserWithRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(
            value = "select userID, name, pass, active, roleID, authority from vwUserWithRole where name = :name and pass = :pass",
            nativeQuery = true
    )
    VwUserWithRole findActiveUserForLogin(@Param("name") String name, @Param("pass") String pass);

}
