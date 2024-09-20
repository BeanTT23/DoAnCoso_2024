package com.lapstore.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import com.lapstore.entity.Lap;
import com.lapstore.entity.LapCategory;
import com.lapstore.entity.LapImage;
import com.lapstore.entity.Brand;
import com.lapstore.service.impl.LapCategoryServiceImpl;
import com.lapstore.service.LapCategoryService;
import com.lapstore.service.LapImageService;
import com.lapstore.service.LapService;
import com.lapstore.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lapstore.service.UserService;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    @Autowired
    private UserService userService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private LapCategoryService lapCategoryService;
    @Autowired
    private LapService lapService;
    @Autowired
    private LapImageService lapImageService;
    
    @Autowired
    private LapCategoryServiceImpl lapCategoryServiceImpl;

    @GetMapping("/all")
    public String showProductsManagersPage(Model model){

        UserSession.getLoggedUserInfo(userService, model);
        List<LapCategory> lapCategorieList = lapCategoryService.getAllLapCategories();
        model.addAttribute("listProducts", lapCategorieList);
        model.addAttribute("pageTitle", "G16 - Quản lí sản phẩm");
        addLapCateAmount(model, lapCategoryServiceImpl.getAllLapCategories());
        return "/view_admin/products_manager";
    }
    @PostMapping("/save")
    public String addProduct(Model model,
    		@RequestParam("categoryName") String categoryName,
    		@RequestParam("brandName") String brandName,
    		@RequestParam("size") String size,
    		@RequestParam("weight") String weight,
    		@RequestParam("coverPhoto") MultipartFile coverPhoto,
    		@RequestParam("shortDescription") String shortDescription,
    		@RequestParam("longDescription") String longDescription,
    		@RequestParam(required = false, name = "color") String color,
    		@RequestParam(required = false, name = "quantity") String quantity,
    		@RequestParam(required = false, name = "price") String price,
    		@RequestParam(required = false, name = "listImage") ArrayList<MultipartFile> listImage
    		) throws IOException{
    	Brand brand = new Brand(brandName);
    	brandService.addOrUpdateBrand(brand);
    	String imageDataString = Base64.getEncoder().encodeToString(coverPhoto.getBytes());
    	LapCategory lapCategory = new LapCategory(brand, categoryName, size, Integer.parseInt(weight),
    			imageDataString, shortDescription, longDescription, LocalDate.now());
    	lapCategoryService.addOrUpdateLapCategory(lapCategory);
    	Lap lap = new Lap(lapCategory, color, new BigDecimal(Double.parseDouble(price)), Integer.parseInt(quantity));
    	lapService.addOrUpdateLap(lap);
    	for (MultipartFile m : listImage) {
    		String imageString = Base64.getEncoder().encodeToString(m.getBytes());
			LapImage lapImage = new LapImage(lap, imageString);
			lapImageService.addOrUpdateLapImage(lapImage);
		}
    	
        UserSession.getLoggedUserInfo(userService, model);
        model.addAttribute("pageTitle", "G16 - Quản lí sản phẩm");
        List<LapCategory> lapCategorieList = lapCategoryService.getAllLapCategories();
        model.addAttribute("listProducts", lapCategorieList);
        
        List<LapCategory> lapCategories = lapCategoryServiceImpl.getAllLapCategories();
        
        model.addAttribute("lapCates", lapCategories);
        
        return "/view_admin/products_manager";
    }
    
    private void addLapCateAmount(Model model, List<LapCategory> lapCategories) {
    	 
		List<Integer> lapCateAmount = new ArrayList<>();
		
		lapCateAmount.add(lapCategories.size());
		lapCateAmount.add(lapService.sumQuantity());
		lapCateAmount.add(lapService.countLap());
		lapCateAmount.add(lapService.countLapNotInStock());

		model.addAttribute("lapCateTotalCate", lapCateAmount.get(0));
		model.addAttribute("lapCateTotal", lapCateAmount.get(1));
		model.addAttribute("lapCateInStock", lapCateAmount.get(2));
		model.addAttribute("lapCateOutOfStock", lapCateAmount.get(3));
	}
}
