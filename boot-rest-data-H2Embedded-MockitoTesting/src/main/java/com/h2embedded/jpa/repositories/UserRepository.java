package com.h2embedded.jpa.repositories;

import com.h2embedded.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Query(value = "update User set username = :username, password = :password where org_id = :orgId and id = :userId", nativeQuery = true)
    void updateUser(@Param("username") String username, @Param("password") String password,
                    @Param("orgId") Long orgId, @Param("userId") Long userId);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM User u WHERE u.org_id = :orgId and u.id = :userId", nativeQuery = true)
    User findUser(@Param("orgId") Long orgId, @Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("delete from User where org_id = :orgId and id = :userId")
    void deleteUserByIds(@Param("orgId") Long orgId, @Param("userId") Long userId);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM User u WHERE u.org_id = :orgId", nativeQuery = true)
    List<User> getAllByOrganization(@Param("orgId") Long orgId);

}
