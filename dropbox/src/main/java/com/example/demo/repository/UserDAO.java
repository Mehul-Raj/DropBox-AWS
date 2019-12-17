package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, String>{

	List<User> findByeMail(String emailId);
	 
	//List <User> user=findByEmail();
	
	/*
	 * @Query(value="select * from user where e_mail=?1",nativeQuery = true) public
	 * List<User> findUserEMail(String email);
	 */
	

}
