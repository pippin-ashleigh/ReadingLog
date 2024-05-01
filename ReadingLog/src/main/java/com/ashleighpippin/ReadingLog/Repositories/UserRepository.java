package com.ashleighpippin.ReadingLog.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashleighpippin.ReadingLog.Models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public Optional<User> findByEmail(String Email);
	
	public List<User> findAll();

}



