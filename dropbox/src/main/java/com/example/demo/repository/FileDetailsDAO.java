package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FileDetails;

public interface FileDetailsDAO extends JpaRepository<FileDetails, Integer> {

}
