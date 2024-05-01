package com.ashleighpippin.ReadingLog.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashleighpippin.ReadingLog.Models.Tag;
import com.ashleighpippin.ReadingLog.Repositories.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository tagRepo;

	//CREATE
	public Tag addTag(Tag tag) {
		return tagRepo.save(tag);
	}
	
	//READ ONE
	public Tag findBySubject(String subject) {
		return tagRepo.findBySubjectIs(subject);
	}
	
	//READ ALL
	public List<Tag> allTags(){
		return tagRepo.findAll();
	}
	
	//UPDATE - N/A
	
	//DELETE - N/A

}
