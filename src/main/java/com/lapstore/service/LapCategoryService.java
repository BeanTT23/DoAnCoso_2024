package com.lapstore.service;

import java.time.LocalDate;
import java.util.List;

import com.lapstore.entity.LapCategory;

public interface LapCategoryService {
	List<LapCategory> getAllLapCategories();
	LapCategory getLapCategoryByID(int lapCategoryID);
	void addOrUpdateLapCategory(LapCategory lapCategory);
	List<LapCategory> getLapCategoriesByName(String name);
	List<LapCategory> getLapCategoriesLikeName(String name);
	List<LapCategory> getLapCategoriesByImportDate(LocalDate importDate);
	List<LapCategory> getLapCategoriesOrderByNameFromA2Z();
	List<LapCategory> getLapCategoriesOrderByNameFromZ2A();
	List<LapCategory> getLapCategoriesOrderByPriceAsc();
	List<LapCategory> getLapCategoriesOrderByPriceDesc();
	List<LapCategory> getLapCategoriesByNewestDate();
	List<LapCategory> searchLapCaterogyALikeByKeyword(String keyword);
}