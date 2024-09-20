package com.lapstore.service.impl;

import java.util.List;
import java.util.Optional;

import com.lapstore.entity.LapImage;
import com.lapstore.service.LapImageService;
import com.lapstore.repository.LapImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagImageServiceImpl implements LapImageService {

	@Autowired
	private LapImageRepository bagImageRepo;
	
	@Override
	public List<LapImage> getAllBagImages() {
		return bagImageRepo.findAll();
	}

	@Override
	public List<LapImage> getBagImagesByBagID(int bagID) {
		return bagImageRepo.getBagImagesByBagID(bagID);
	}

	@Override
	public LapImage getBagImageByID(int bagImageID) {
		Optional<LapImage> result = bagImageRepo.findById(bagImageID);
		LapImage bagImage = null;
		if(result.isPresent()) {
			bagImage = result.get();
		} else {
			throw new RuntimeException("Did not find bag image id - " + bagImageID);
		}
		return bagImage;
	}

	@Override
	public void addOrUpdateBagImage(LapImage bagImage) {
		bagImageRepo.save(bagImage);
	}
}
