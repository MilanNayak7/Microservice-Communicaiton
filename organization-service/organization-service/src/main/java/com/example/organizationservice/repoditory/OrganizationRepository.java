package com.example.organizationservice.repoditory;

import com.example.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository  extends JpaRepository<Organization,Long> {

    Optional<Organization> getByOrganizationCode(String organizationCode);
}
