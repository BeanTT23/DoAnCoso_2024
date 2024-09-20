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
public class LapCategoryServiceImpl implements LapCategoryService {

	@Autowired
	private LapServiceImpl lapServiceImpl;
	
	@Autowired
	private LapCategoryRepository lapCategoryRepo;

	@Override
	public List<LapCategory> getAllLapCategories() {
		return lapCategoryRepo.findAll();
	}

	@Override
	public LapCategory getLapCategoryByID(int lapCategoryID) {
		return lapCategoryRepo.findById(lapCategoryID).get();
	}

	@Override
	public List<LapCategory> getLapCategoriesByName(String name) {
		return lapCategoryRepo.getLapCategoriesByName(name);
	}

	@Override
	public List<LapCategory> getLapCategoriesByImportDate(LocalDate importDate) {
		return lapCategoryRepo.getLapCategoriesByImportDate(importDate);
	}

//	@Override
//	public double getMinPriceOfLapCategory(int lapCategoryId) {
//		return lapCategoryRepo.getMinPriceOfLapCategory(lapCategoryId);
//	}

	@Override
	public List<LapCategory> getLapCategoriesLikeName(String name) {
		return lapCategoryRepo.getLapCategoriesLikeName("%" + name + "%");
	}

	@Override
	public List<LapCategory> getLapCategoriesOrderByNameFromA2Z() {
		return lapCategoryRepo.getLapCategoriesOrderByNameFromA2Z();
	}

	@Override
	public List<LapCategory> getLapCategoriesOrderByNameFromZ2A() {
		return lapCategoryRepo.getLapCategoriesOrderByNameFromZ2A();
	}

	@Override
	public List<LapCategory> getLapCategoriesOrderByPriceAsc() {
		List<LapCategory> lapCategories = new ArrayList<>();
		
		LinkedHashSet<Integer> cateIdList = lapServiceImpl.getLapCategoryIdOrderByPriceAsc();
		
		cateIdList.forEach(cateId -> {
			lapCategories.add(getLapCategoryByID(cateId));
		});
		
		return lapCategories;
	}

	@Override
	public List<LapCategory> getLapCategoriesOrderByPriceDesc() {
		List<LapCategory> lapCategories = new ArrayList<>();
		
		LinkedHashSet <Integer> cateIdList = lapServiceImpl.getLapCategoryIdOrderByPriceDesc();
		
		cateIdList.forEach(cateId -> {
			lapCategories.add(getLapCategoryByID(cateId));
		});
		
		return lapCategories;
	}

	@Override
	public List<LapCategory> getLapCategoriesByNewestDate() {
		return lapCategoryRepo.getLapCategoriesByNewestDate();
	}

	@Override
	public void addOrUpdateLapCategory(LapCategory lapCategory) {
		lapCategoryRepo.save(lapCategory);
  }
    
  @Override
	public List<LapCategory> searchLapCaterogyALikeByKeyword(String keyword) {
		return lapCategoryRepo.searchLapCaterogyALikeByKeyword("%" + keyword + "%");
	}
}