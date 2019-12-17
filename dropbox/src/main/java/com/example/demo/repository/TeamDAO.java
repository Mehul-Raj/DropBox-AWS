package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Team;

public interface TeamDAO extends JpaRepository<Team, Integer> {

}
