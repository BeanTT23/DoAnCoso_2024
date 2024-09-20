package com.lapstore.service;

import java.util.List;

import com.lapstore.entity.LapReview;

public interface LapReviewService {
	List<LapReview> getAllLapReview();
	List<LapReview> getLapReviewsByLapID(int lapID);
	LapReview getLapReviewByID(int lapReviewID);
	void addOrUpdateLapReview(LapReview lapReview);
}
