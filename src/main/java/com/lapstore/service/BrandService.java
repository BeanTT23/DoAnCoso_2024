package com.lapstore.service;

import java.util.List;

import com.lapstore.entity.Brand;

public interface BrandService {
	List<Brand> getAllBrand();
	Brand getBrandByBrandID(int brandID);
	void addOrUpdateBrand(Brand brand);
}
