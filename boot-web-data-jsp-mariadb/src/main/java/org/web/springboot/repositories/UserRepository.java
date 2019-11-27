package org.web.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.web.springboot.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

}
