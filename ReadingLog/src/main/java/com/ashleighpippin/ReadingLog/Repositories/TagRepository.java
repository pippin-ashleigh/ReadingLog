package com.ashleighpippin.ReadingLog.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ashleighpippin.ReadingLog.Models.Tag;


@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{
	Tag findBySubjectIs(String subject);
	public List<Tag> findAll();
	
}
