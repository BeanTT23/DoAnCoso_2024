package com.lapstore.service.impl;

import java.util.List;
import java.util.Optional;

import com.lapstore.entity.Brand;
import com.lapstore.repository.BrandRepository;
import com.lapstore.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandRepository brandRepo;
	
	@Override
	public List<Brand> getAllBrand() {
		return brandRepo.findAll();
	}

	@Override
	public Brand getBrandByBrandID(int brandID) {
		Optional<Brand> result = brandRepo.findById(brandID);
		Brand brand = null;
		if(result.isPresent()) {
			brand = result.get();
		} else {
			throw new RuntimeException("Did not find brand id - " + brandID);
		}
		return brand;
	}

	@Override
	public void addOrUpdateBrand(Brand brand) {
		brandRepo.save(brand);
	}

}
