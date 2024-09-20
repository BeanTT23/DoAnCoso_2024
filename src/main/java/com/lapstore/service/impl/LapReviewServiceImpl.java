package com.lapstore.service.impl;

import java.util.List;
import java.util.Optional;

import com.lapstore.entity.LapReview;
import com.lapstore.service.LapReviewService;
import com.lapstore.repository.LapReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LapReviewServiceImpl implements LapReviewService {

	@Autowired
	private LapReviewRepository lapReviewRepo;
	
	@Override
	public List<LapReview> getAllLapReview() {
		return lapReviewRepo.findAll();
	}

	@Override
	public List<LapReview> getLapReviewsByLapID(int lapID) {
		return lapReviewRepo.getLapReviewsByLapID(lapID);
	}

	@Override
	public LapReview getLapReviewByID(int lapReviewID) {
		Optional<LapReview> result = lapReviewRepo.findById(lapReviewID);
		LapReview lapReview = null;
		if(result.isPresent()) {
			lapReview = result.get();
		} else {
			throw new RuntimeException("Did not find lap review id - " + lapReviewID);
		}
		return lapReview;
	}

	@Override
	public void addOrUpdateLapReview(LapReview lapReview) {
		lapReviewRepo.save(lapReview);
	}

}
