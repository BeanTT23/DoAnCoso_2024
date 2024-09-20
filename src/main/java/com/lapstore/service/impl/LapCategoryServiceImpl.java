package com.lapstore.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.lapstore.entity.LapCategory;
import com.lapstore.service.LapCategoryService;
import com.lapstore.repository.LapCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagCategoryServiceImpl implements LapCategoryService {

	@Autowired
	private BagServiceImpl bagServiceImpl;
	
	@Autowired
	private LapCategoryRepository bagCategoryRepo;

	@Override
	public List<LapCategory> getAllBagCategories() {
		return bagCategoryRepo.findAll();
	}

	@Override
	public LapCategory getBagCategoryByID(int bagCategoryID) {
		return bagCategoryRepo.findById(bagCategoryID).get();
	}

	@Override
	public List<LapCategory> getBagCategoriesByName(String name) {
		return bagCategoryRepo.getBagCategoriesByName(name);
	}

	@Override
	public List<LapCategory> getBagCategoriesByImportDate(LocalDate importDate) {
		return bagCategoryRepo.getBagCategoriesByImportDate(importDate);
	}

//	@Override
//	public double getMinPriceOfBagCategory(int bagCategoryId) {
//		return bagCategoryRepo.getMinPriceOfBagCategory(bagCategoryId);
//	}

	@Override
	public List<LapCategory> getBagCategoriesLikeName(String name) {
		return bagCategoryRepo.getBagCategoriesLikeName("%" + name + "%");
	}

	@Override
	public List<LapCategory> getBagCategoriesOrderByNameFromA2Z() {
		return bagCategoryRepo.getBagCategoriesOrderByNameFromA2Z();
	}

	@Override
	public List<LapCategory> getBagCategoriesOrderByNameFromZ2A() {
		return bagCategoryRepo.getBagCategoriesOrderByNameFromZ2A();
	}

	@Override
	public List<LapCategory> getBagCategoriesOrderByPriceAsc() {
		List<LapCategory> bagCategories = new ArrayList<>();
		
		LinkedHashSet<Integer> cateIdList = bagServiceImpl.getBagCategoryIdOrderByPriceAsc();
		
		cateIdList.forEach(cateId -> {
			bagCategories.add(getBagCategoryByID(cateId));
		});
		
		return bagCategories;
	}

	@Override
	public List<LapCategory> getBagCategoriesOrderByPriceDesc() {
		List<LapCategory> bagCategories = new ArrayList<>();
		
		LinkedHashSet <Integer> cateIdList = bagServiceImpl.getBagCategoryIdOrderByPriceDesc();
		
		cateIdList.forEach(cateId -> {
			bagCategories.add(getBagCategoryByID(cateId));
		});
		
		return bagCategories;
	}

	@Override
	public List<LapCategory> getBagCategoriesByNewestDate() {
		return bagCategoryRepo.getBagCategoriesByNewestDate();
	}

	@Override
	public void addOrUpdateBagCategory(LapCategory bagCategory) {
		bagCategoryRepo.save(bagCategory);
  }
    
  @Override
	public List<LapCategory> searchBagCaterogyALikeByKeyword(String keyword) {
		return bagCategoryRepo.searchBagCaterogyALikeByKeyword("%" + keyword + "%");
	}
}