package com.lapstore.service.impl;

import java.util.List;
import java.util.Optional;

import com.lapstore.entity.LapReview;
import com.lapstore.service.LapReviewService;
import com.lapstore.repository.LapReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagReviewServiceImpl implements LapReviewService {

	@Autowired
	private LapReviewRepository bagReviewRepo;
	
	@Override
	public List<LapReview> getAllBagReview() {
		return bagReviewRepo.findAll();
	}

	@Override
	public List<LapReview> getBagReviewsByBagID(int bagID) {
		return bagReviewRepo.getBagReviewsByBagID(bagID);
	}

	@Override
	public LapReview getBagReviewByID(int bagReviewID) {
		Optional<LapReview> result = bagReviewRepo.findById(bagReviewID);
		LapReview bagReview = null;
		if(result.isPresent()) {
			bagReview = result.get();
		} else {
			throw new RuntimeException("Did not find bag review id - " + bagReviewID);
		}
		return bagReview;
	}

	@Override
	public void addOrUpdateBagReview(LapReview bagReview) {
		bagReviewRepo.save(bagReview);
	}

}
