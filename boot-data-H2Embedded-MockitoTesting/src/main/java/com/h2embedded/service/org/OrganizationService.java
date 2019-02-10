package com.h2embedded.service.org;

import com.h2embedded.entity.Organization;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrganizationService {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    Organization save(Organization organization);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    Organization updateById(Long id, Organization organization);

    @Transactional(readOnly = true)
    Organization findById(Long id);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    void delete(Long id);

    @Transactional(readOnly = true)
    List<Organization> getAll();
}
