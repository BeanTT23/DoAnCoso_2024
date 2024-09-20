package com.lapstore.service;

import java.time.LocalDate;
import java.util.List;

import com.lapstore.entity.LapCategory;

public interface BagCategoryService {
	List<LapCategory> getAllBagCategories();
	LapCategory getBagCategoryByID(int bagCategoryID);
	void addOrUpdateBagCategory(LapCategory bagCategory);
	List<LapCategory> getBagCategoriesByName(String name);
	List<LapCategory> getBagCategoriesLikeName(String name);
	List<LapCategory> getBagCategoriesByImportDate(LocalDate importDate);
	List<LapCategory> getBagCategoriesOrderByNameFromA2Z();
	List<LapCategory> getBagCategoriesOrderByNameFromZ2A();
	List<LapCategory> getBagCategoriesOrderByPriceAsc();
	List<LapCategory> getBagCategoriesOrderByPriceDesc();
	List<LapCategory> getBagCategoriesByNewestDate();
	List<LapCategory> searchBagCaterogyALikeByKeyword(String keyword);
}