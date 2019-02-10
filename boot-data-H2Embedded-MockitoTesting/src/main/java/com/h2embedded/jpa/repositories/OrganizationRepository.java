package com.h2embedded.jpa.repositories;

import com.h2embedded.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Modifying
    @Transactional
    @Query("delete from Organization where id = :id")
    void deleteOrganizationById(@Param("id") Long id);

}
