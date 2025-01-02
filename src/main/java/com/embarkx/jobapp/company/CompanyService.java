package com.embarkx.jobapp.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company getCompany(Long id);

    Boolean updateCompany(Long id, Company newCompany);

    Boolean createCompany(Company company);

    boolean removeCompany(Long id);
}
