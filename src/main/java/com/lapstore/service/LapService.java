package com.lapstore.service;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;

import com.g16.handbagstore.entity.Bag;
import com.lapstore.entity.LapCategory;

public interface BagService {
	List<Bag> getAllBags();
	Bag getBagByID(int bagID);
	void addOrUpdateBag(Bag bag);
	List<Bag> getBagListOfBagCategory(int bagCategoryId);
	BigDecimal getBagPriceByCateID(int cateID);
	List<String> listPrice(List<LapCategory> listBagCategory);
	LinkedHashSet<Integer> getBagCategoryIdOrderByPriceAsc();
	LinkedHashSet<Integer> getBagCategoryIdOrderByPriceDesc();
	int countBag();
	int countBagNotInStock();
	int sumQuantity();
}