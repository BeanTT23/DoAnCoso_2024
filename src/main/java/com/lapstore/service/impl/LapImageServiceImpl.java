package com.lapstore.service.impl;

import java.util.List;
import java.util.Optional;

import com.lapstore.entity.LapImage;
import com.lapstore.service.LapImageService;
import com.lapstore.repository.LapImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LapImageServiceImpl implements LapImageService {

	@Autowired
	private LapImageRepository lapImageRepo;
	
	@Override
	public List<LapImage> getAllLapImages() {
		return lapImageRepo.findAll();
	}

	@Override
	public List<LapImage> getLapImagesByLapID(int lapID) {
		return lapImageRepo.getLapImagesByLapID(lapID);
	}

	@Override
	public LapImage getLapImageByID(int lapImageID) {
		Optional<LapImage> result = lapImageRepo.findById(lapImageID);
		LapImage lapImage = null;
		if(result.isPresent()) {
			lapImage = result.get();
		} else {
			throw new RuntimeException("Did not find lap image id - " + lapImageID);
		}
		return lapImage;
	}

	@Override
	public void addOrUpdateLapImage(LapImage lapImage) {
		lapImageRepo.save(lapImage);
	}
}
