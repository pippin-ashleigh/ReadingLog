package com.ashleighpippin.ReadingLog.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashleighpippin.ReadingLog.Models.Reading;
import com.ashleighpippin.ReadingLog.Models.User;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, Long>{

	public List<Reading> findAll();
}
