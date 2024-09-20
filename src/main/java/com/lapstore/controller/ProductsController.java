package com.lapstore.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import com.lapstore.entity.Lap;
import com.lapstore.entity.LapCategory;
import com.lapstore.entity.CartDetail;
import com.lapstore.entity.CartHeader;
import com.lapstore.service.LapCategoryService;
import com.lapstore.service.UserService;
import com.lapstore.service.impl.LapServiceImpl;
import com.lapstore.service.impl.CartDetailServiceImpl;
import com.lapstore.service.impl.CartHeaderServiceImpl;
import com.lapstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
    @Autowired
    private UserService userService;

    @Autowired
    private LapServiceImpl lapServiceImpl;
    
    @Autowired
    private LapCategoryService lapCategoryService;
    
    @Autowired
	private CartHeaderServiceImpl cartHeaderServiceImpl;

	@Autowired
	private CartDetailServiceImpl cartDetailServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;

    @GetMapping("/all")
    public String showAllProducts(Model model){
        UserSession.getLoggedUserInfo(userService, model);

        List<LapCategory> listLapCategory = lapCategoryService.getAllLapCategories();

        List<String> lapCatePriceList = lapServiceImpl.listPrice(listLapCategory);


        model.addAttribute("resultSize", listLapCategory.size());
        model.addAttribute("pageTitle", "Tất cả sản phẩm");
        model.addAttribute("listProducts", listLapCategory);
        model.addAttribute("listProductsPrice", lapCatePriceList);
        
        return "/view_customer/all_products";
    }
    
    @GetMapping("/all/filter")
    public String showAllProducts(Model model, @RequestParam String sort){
        UserSession.getLoggedUserInfo(userService, model);

        List<LapCategory> listLapCategory = new ArrayList<>();
        
        if(sort.equals("1"))
        	listLapCategory = lapCategoryService.getLapCategoriesOrderByNameFromA2Z();
        else if(sort.equals("2"))
        	listLapCategory = lapCategoryService.getLapCategoriesOrderByNameFromZ2A();
        else if(sort.equals("3"))
        	listLapCategory = lapCategoryService.getLapCategoriesOrderByPriceAsc();
        else if(sort.equals("4"))
        	listLapCategory = lapCategoryService.getLapCategoriesOrderByPriceDesc();

        List<String> lapCatePriceList = lapServiceImpl.listPrice(listLapCategory);
        
        model.addAttribute("resultSize", listLapCategory.size());
        model.addAttribute("pageTitle", "Tất cả sản phẩm");
        model.addAttribute("listProducts", listLapCategory);
        model.addAttribute("listProductsPrice", lapCatePriceList);
        
        return "/view_customer/all_products";
    }

    @GetMapping("/newest")
    public String showNewestProducts(Model model){
    	UserSession.getLoggedUserInfo(userService, model);

        List<LapCategory> listLapCategory = lapCategoryService.getLapCategoriesByNewestDate();

        List<String> lapCatePriceList = lapServiceImpl.listPrice(listLapCategory);
        
        model.addAttribute("resultSize", listLapCategory.size());
        model.addAttribute("pageTitle", "Tất cả sản phẩm");
        model.addAttribute("listProducts", listLapCategory);
        model.addAttribute("listProductsPrice", lapCatePriceList);
        return "view_customer/all_products";
    }


    @GetMapping("/product/{categoryId}")
    public String showProductDetail(@PathVariable("categoryId") int categoryId, Model model){
        UserSession.getLoggedUserInfo(userService, model);

        LapCategory lapCategory = lapCategoryService.getLapCategoryByID(categoryId);

        model.addAttribute("lapCategory", lapCategory);
        model.addAttribute("pageTitle", lapCategory.getName());

        return "/view_customer/product_detail";
    }


    @PostMapping("/product/add_to_cart")
	public String addToCart(
			@RequestParam int cusID, @RequestParam int orderQuantity,
			@RequestParam int lapID, @RequestParam int lapCateID
	) {

		CartHeader cartHeader = cartHeaderServiceImpl.getCartHeaderByID(cusID);
		Lap lap = lapServiceImpl.getLapByID(lapID);
		
		if(cartHeader != null) {
			CartDetail cartDetail = cartDetailServiceImpl.getCartDetailByCartHeaderIdAndLapId(cusID, lapID);
			
			if(cartDetail != null)
				cartDetail.setLapQty(cartDetail.getLapQty() + orderQuantity);
			else
				cartDetail = new CartDetail(cartHeader, lap, orderQuantity);
				
			cartDetailServiceImpl.addOrUpdateCartDetail(cartDetail);

			cartHeader
					.setTotalPrice(cartHeader.getTotalPrice()
							.add(lap.getPrice().multiply(new BigDecimal(orderQuantity))));

			cartHeader.setTotalQuantity(cartHeader.getTotalQuantity() + orderQuantity);

			cartHeaderServiceImpl.addOrUpdateCartHeader(cartHeader);
		}
		else {
			cartHeader = new CartHeader(new BigDecimal(0), 0, userServiceImpl.getUserByUserID(cusID));
			CartDetail cartDetail = new CartDetail(cartHeader, lap, orderQuantity);
			
			cartDetailServiceImpl.addOrUpdateCartDetail(cartDetail);

			cartHeader
					.setTotalPrice(cartHeader.getTotalPrice()
							.add(lap.getPrice().multiply(new BigDecimal(orderQuantity))));

			cartHeader.setTotalQuantity(cartHeader.getTotalQuantity() + orderQuantity);

			cartHeaderServiceImpl.addOrUpdateCartHeader(cartHeader);
		}
		
		return "redirect:/products/product/" + lapCateID + "?added";
	}
}
