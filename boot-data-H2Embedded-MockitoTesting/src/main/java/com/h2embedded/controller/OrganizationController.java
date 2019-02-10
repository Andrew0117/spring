package com.h2embedded.controller;

import com.h2embedded.entity.Organization;
import com.h2embedded.service.org.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orgs")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/orgs")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Organization addOrganization(
            @RequestBody Organization organization
    ) {
        organization = organizationService.save(organization);

        return organization;
    }

    @PutMapping("/{orgId}")
    public ResponseEntity<Organization> updateOrganization(
            @PathVariable("orgId") Long orgId,
            @RequestBody Organization organization
    ) {
        organization = organizationService.updateById(orgId, organization);

        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("orgId") Long orgId) {
        Organization organization = organizationService.findById(orgId);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @DeleteMapping("/{orgId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("orgId") Long orgId) {
        organizationService.delete(orgId);
    }

    @GetMapping("/orgs")
    public ResponseEntity<List<Organization>> getAllOrganization() {
        List<Organization> list = organizationService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
