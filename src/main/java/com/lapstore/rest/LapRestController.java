package com.lapstore.rest;

import java.util.ArrayList;
import java.util.List;

import com.lapstore.entity.Lap;
import com.lapstore.service.LapCategoryService;
import com.lapstore.service.LapService;
import com.lapstore.entity.LapCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class LapRestController {
	@Autowired
	private LapService lapService;

	@Autowired
	private LapCategoryService lapCategoryService;

	@GetMapping("/laps")
	public List<Lap> getLaps() {
		return lapService.getAllLaps();
	}

	@GetMapping("/laps/{cate_name}")
	public List<LapCategory> getLapsByCateName(@PathVariable String cate_name) {
		return lapCategoryService.getLapCategoriesByName(cate_name);
	}

	@GetMapping("/laps/search/{name}")
	public List<LapCategory> getLapsLikeCateName(@PathVariable String name) {
		return lapCategoryService.getLapCategoriesLikeName(name);
	}

	@GetMapping("/laps/lapCategory={lapCategoryId}")
	public List<Lap> findLapsListByLapCategoryId(@PathVariable int lapCategoryId) {
		return lapService.getLapListOfLapCategory(lapCategoryId);
	}

	@GetMapping("/laps/search/keyword")
	public List<String> search(@RequestParam String keyword) {
		
		List<LapCategory>  lapCategories = lapCategoryService.searchLapCaterogyALikeByKeyword(keyword);
		List<String> res  = new ArrayList<>();
	
		lapCategories.forEach(lapCate -> {
			res.add(String.format("{\"lapCateID\":%d, \"name\":\"%s\", \"brand\":\"%s\"}",
					lapCate.getLapCategoryId(), lapCate.getName(), lapCate.getBrand().getName()));
		});
	    
		return res; 
	}
}