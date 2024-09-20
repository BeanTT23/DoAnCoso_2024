package com.lapstore.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.lapstore.entity.Lap;
import com.lapstore.entity.LapCategory;
import com.lapstore.service.LapService;
import com.lapstore.repository.LapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LapServiceImpl implements LapService {

	@Autowired
	private LapRepository lapRepo;
	
	@Override
	public List<Lap> getAllLaps() {
		return lapRepo.findAll();
	}

	@Override
	public Lap getLapByID(int lapID) {
		return lapRepo.findById(lapID).get();
	}

	@Override
	public void addOrUpdateLap(Lap lap) {
		lapRepo.save(lap);
	}

	@Override
	public List<Lap> getLapListOfLapCategory(int lapCategoryId) {
		return lapRepo.findLapsListByLapCategoryId(lapCategoryId);
	}

	@Override
	public BigDecimal getLapPriceByCateID(int cateID) {
		return lapRepo.getLapPriceByCateID(cateID);
	}

	@Override
	public List<String> listPrice(List<LapCategory> listLapCategory) {
		List<String> listPrice = new ArrayList<>();
		
		listLapCategory.forEach(lapCate -> {
			listPrice.add(new DecimalFormat("#,###")
					.format(getLapPriceByCateID(lapCate.getLapCategoryId())));
		});
		
		return listPrice;
	}

	@Override
	public LinkedHashSet<Integer> getLapCategoryIdOrderByPriceAsc() {
		return lapRepo.getLapCategoryIdOrderByPriceAsc();
	}

	@Override
	public LinkedHashSet<Integer> getLapCategoryIdOrderByPriceDesc() {
		return lapRepo.getLapCategoryIdOrderByPriceDesc();
	}

	@Override
	public int countLap() {
		return lapRepo.countLap();
	}

	@Override
	public int sumQuantity() {
		return lapRepo.sumQuantity();
	}

	@Override
	public int countLapNotInStock() {
		return lapRepo.countLapNotInStock();
	}
}