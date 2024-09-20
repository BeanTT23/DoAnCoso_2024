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
public class BagServiceImpl implements LapService {

	@Autowired
	private LapRepository bagRepo;
	
	@Override
	public List<Lap> getAllBags() {
		return bagRepo.findAll();
	}

	@Override
	public Lap getBagByID(int bagID) {
		return bagRepo.findById(bagID).get();
	}

	@Override
	public void addOrUpdateBag(Bag bag) {
		bagRepo.save(bag);
	}

	@Override
	public List<Lap> getBagListOfBagCategory(int bagCategoryId) {
		return bagRepo.findBagsListByBagCategoryId(bagCategoryId);
	}

	@Override
	public BigDecimal getBagPriceByCateID(int cateID) {
		return bagRepo.getBagPriceByCateID(cateID);
	}

	@Override
	public List<String> listPrice(List<LapCategory> listBagCategory) {
		List<String> listPrice = new ArrayList<>();
		
		listBagCategory.forEach(bagCate -> {
			listPrice.add(new DecimalFormat("#,###")
					.format(getBagPriceByCateID(bagCate.getLapCategoryId())));
		});
		
		return listPrice;
	}

	@Override
	public LinkedHashSet<Integer> getBagCategoryIdOrderByPriceAsc() {
		return bagRepo.getBagCategoryIdOrderByPriceAsc();
	}

	@Override
	public LinkedHashSet<Integer> getBagCategoryIdOrderByPriceDesc() {
		return bagRepo.getBagCategoryIdOrderByPriceDesc();
	}

	@Override
	public int countBag() {
		return bagRepo.countBag();
	}

	@Override
	public int sumQuantity() {
		return bagRepo.sumQuantity();
	}

	@Override
	public int countBagNotInStock() {
		return bagRepo.countBagNotInStock();
	}
}