package com.lapstore.service;

import java.util.List;

import com.lapstore.entity.LapImage;

public interface BagImageService {
	List<LapImage> getAllBagImages();
	List<LapImage> getBagImagesByBagID(int bagID);
	LapImage getBagImageByID(int bagImageID);
	void addOrUpdateBagImage(LapImage bagImage);
}
