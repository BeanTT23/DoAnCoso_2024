package com.lapstore.rest;

import java.util.ArrayList;
import java.util.List;

import com.lapstore.service.LapCategoryService;
import com.lapstore.service.LapService;
import com.lapstore.entity.LapCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g16.handbagstore.entity.Bag;

@RestController
@RequestMapping("/api")
public class BagRestController {
	@Autowired
	private LapService bagService;

	@Autowired
	private LapCategoryService bagCategoryService;

	@GetMapping("/bags")
	public List<Bag> getBags() {
		return bagService.getAllBags();
	}

	@GetMapping("/bags/{cate_name}")
	public List<LapCategory> getBagsByCateName(@PathVariable String cate_name) {
		return bagCategoryService.getBagCategoriesByName(cate_name);
	}

	@GetMapping("/bags/search/{name}")
	public List<LapCategory> getBagsLikeCateName(@PathVariable String name) {
		return bagCategoryService.getBagCategoriesLikeName(name);
	}

	@GetMapping("/bags/bagCategory={bagCategoryId}")
	public List<Bag> findBagsListByBagCategoryId(@PathVariable int bagCategoryId) {
		return bagService.getBagListOfBagCategory(bagCategoryId);
	}

	@GetMapping("/bags/search/keyword")
	public List<String> search(@RequestParam String keyword) {
		
		List<LapCategory>  bagCategories = bagCategoryService.searchBagCaterogyALikeByKeyword(keyword);
		List<String> res  = new ArrayList<>();
	
		bagCategories.forEach(bagCate -> {
			res.add(String.format("{\"bagCateID\":%d, \"name\":\"%s\", \"brand\":\"%s\"}",
					bagCate.getBagCategoryId(), bagCate.getName(), bagCate.getBrand().getName()));
		});
	    
		return res; 
	}
}