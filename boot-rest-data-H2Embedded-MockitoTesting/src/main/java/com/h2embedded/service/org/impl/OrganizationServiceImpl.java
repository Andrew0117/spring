package com.h2embedded.service.org.impl;

import com.h2embedded.jpa.repositories.OrganizationRepository;
import com.h2embedded.entity.Organization;
import com.h2embedded.service.org.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization save(Organization organization) {
        organization = organizationRepository.save(organization);
        return organization;
    }

    @Override
    public Organization updateById(Long id, Organization organization) {
        Organization org = organizationRepository.getOne(id);
        org.setName(organization.getName());
        org = organizationRepository.save(org);
        return org;
    }

    @Override
    public Organization findById(Long id) {
        Organization organization = organizationRepository.findById(id).get();
        return organization;
    }

    @Override
    public void delete(Long id) {
        organizationRepository.deleteOrganizationById(id);
    }

    @Override
    public List<Organization> getAll() {
        List<Organization> listOrganization = organizationRepository.findAll();
        return listOrganization;
    }
}
