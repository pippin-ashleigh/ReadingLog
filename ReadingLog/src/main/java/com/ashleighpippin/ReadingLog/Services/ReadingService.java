package com.ashleighpippin.ReadingLog.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashleighpippin.ReadingLog.Models.Reading;
import com.ashleighpippin.ReadingLog.Models.Tag;
import com.ashleighpippin.ReadingLog.Models.User;
import com.ashleighpippin.ReadingLog.Repositories.ReadingRepository;

@Service
public class ReadingService {

	@Autowired
	private ReadingRepository readingRepo;

	//CREATE
	public Reading addReading(Reading reading) {
		return readingRepo.save(reading);
	}

	//READ ALL
	public List<Reading> allReadings() {
		return readingRepo.findAll();
	}
	
	//READ ALL BY USER
	public List<Reading> getUserReadings(User user){
		return readingRepo.findAllByUser(user);
	}
	
	//READ ALL BY TAG
	public List<Reading> getTaggedReadings(Tag tag){
		return readingRepo.findAllByTagsContaining(tag);
	}
	
	//READ ALL BY OTHER USERS
	public List<Reading> getOtherUserReadings(User user){
		return readingRepo.findByUserNot(user);
	}

	//READ ONE
	public Reading findByID(Long id) {
		Optional<Reading> optionalReading = readingRepo.findById(id);
		if (optionalReading.isPresent()) {
			return optionalReading.get();
		} else {
			return null;
		}
	}
	
	//UPDATE
	public Reading updateReading(Reading reading) {
		Optional<Reading> optionalReading = readingRepo.findById(reading.getId());
		if(optionalReading.isPresent()) {
			return readingRepo.save(reading);
		}else {
			return null;
		}
	}
	
	//DELETE
	public void deleteReading(Long id) {
		Optional<Reading> optionalReading = readingRepo.findById(id);
		if(optionalReading.isPresent()) {
			readingRepo.deleteById(id);
		}
	}
	

}
