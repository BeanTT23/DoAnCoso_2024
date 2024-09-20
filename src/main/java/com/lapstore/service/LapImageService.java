package com.lapstore.service;

import java.util.List;

import com.lapstore.entity.LapImage;

public interface LapImageService {
	List<LapImage> getAllLapImages();
	List<LapImage> getLapImagesByLapID(int lapID);
	LapImage getLapImageByID(int lapImageID);
	void addOrUpdateLapImage(LapImage lapImage);
}
