package com.embarkx.jobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companyList = companyService.getAllCompanies();
        if (!companyList.isEmpty()) {
            return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable long id) {
        Company company = companyService.getCompany(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable long id, @RequestBody Company newCompany) {

        if (companyService.updateCompany(id, newCompany)) {
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company doesn't exist", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        if (companyService.createCompany(company)) {
            return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company creation failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeJob(@PathVariable Long id) {
        if (companyService.removeCompany(id)) {
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

}

