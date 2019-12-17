package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Company;

public interface CompanyDAO extends JpaRepository<Company, Integer> {
	

}
