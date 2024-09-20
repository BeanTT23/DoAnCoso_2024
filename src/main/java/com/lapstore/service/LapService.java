package com.lapstore.service;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;

import com.lapstore.entity.Lap;
import com.lapstore.entity.LapCategory;

public interface LapService {
	List<Lap> getAllLaps();
	Lap getLapByID(int lapID);
	void addOrUpdateLap(Lap lap);
	List<Lap> getLapListOfLapCategory(int lapCategoryId);
	BigDecimal getLapPriceByCateID(int cateID);
	List<String> listPrice(List<LapCategory> listLapCategory);
	LinkedHashSet<Integer> getLapCategoryIdOrderByPriceAsc();
	LinkedHashSet<Integer> getLapCategoryIdOrderByPriceDesc();
	int countLap();
	int countLapNotInStock();
	int sumQuantity();
}