package com.lapstore.controller;

import java.math.BigDecimal;
import java.util.List;

import com.lapstore.entity.CartDetail;
import com.lapstore.entity.CartHeader;
import com.lapstore.entity.User;
import com.lapstore.service.CartDetailService;
import com.lapstore.service.CartHeaderService;
import com.lapstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartHeaderService cartHeaderService;

    @Autowired
    private CartDetailService cartDetailService;

    @GetMapping("/all")
    public String showCartPage(Model model){
        UserSession.getLoggedUserInfo(userService, model);

        User currentUser = UserSession.getCurrentUser(userService);

        CartHeader cartHeader = cartHeaderService.getCartHeaderByID(currentUser.getCustomerId());

        List<CartDetail> cartDetailList = cartDetailService.getCartDetailsByCartHeaderID(cartHeader.getId());

        model.addAttribute("cartDetailNum", cartDetailList.size());
        model.addAttribute("cartDetailList", cartDetailList);

        model.addAttribute("cartHeader", cartHeader);

        model.addAttribute("pageTitle", "Giỏ hàng của bạn");
        return "view_customer/cart";
    }
    
    @GetMapping("/clear")
    public String clearCart() { 
    	User currentUser = UserSession.getCurrentUser(userService);
    	
    	CartHeader cartHeader = cartHeaderService.getCartHeaderByID(currentUser.getCustomerId());
    	
    	cartDetailService.deleteCartDetailByCartHeaderId(cartHeader.getId());
        cartHeader.setTotalPrice(new BigDecimal(0));
        cartHeader.setTotalQuantity(0);
        cartHeaderService.addOrUpdateCartHeader(cartHeader);
    	
    	return "redirect:/cart/all";
    }
}
