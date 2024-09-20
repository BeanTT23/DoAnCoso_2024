package com.lapstore.service;

import java.util.List;

import com.lapstore.entity.LapReview;

public interface BagReviewService {
	List<LapReview> getAllBagReview();
	List<LapReview> getBagReviewsByBagID(int bagID);
	LapReview getBagReviewByID(int bagReviewID);
	void addOrUpdateBagReview(LapReview bagReview);
}
