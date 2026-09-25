package com.houseviz.user.repository;

import com.houseviz.user.domain.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CompanyRepository extends CrudRepository<Company, UUID> {
}
