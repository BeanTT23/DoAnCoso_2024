package com.lapstore.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lapstore.service.CartHeaderService;
import com.lapstore.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lapstore.entity.CartDetail;
import com.lapstore.entity.CartHeader;
import com.lapstore.entity.SaleOrder;
import com.lapstore.entity.SaleOrderDetail;
import com.lapstore.entity.User;
import com.lapstore.service.CartDetailService;
import com.lapstore.service.SaleOrderDetailService;
import com.lapstore.service.UserService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartHeaderService cartHeaderService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private SaleOrderService saleOrderService;

    @Autowired
    private SaleOrderDetailService saleOrderDetailService;

    @GetMapping("/all")
    public String showCheckoutPage(Model model){
        User currentUser = UserSession.getCurrentUser(userService);

        UserSession.getLoggedUserInfo(userService, model);

        CartHeader cartHeader = cartHeaderService.getCartHeaderByID(currentUser.getCustomerId());

        List<CartDetail> cartDetailList = cartDetailService.getCartDetailsByCartHeaderID(cartHeader.getId());

        model.addAttribute("user", currentUser);
        model.addAttribute("cartDetailList", cartDetailList);
        model.addAttribute("cartHeader", cartHeader);
        model.addAttribute("pageTitle", "Tiến hành đặt hàng");

        return "view_customer/checkout";
    }

    @GetMapping("/done")
    public String showThankYouPage(Model model,
    		@RequestParam("address") String address,
    		@RequestParam("phone") String phone) {
    	User currentUser = UserSession.getCurrentUser(userService);

        UserSession.getLoggedUserInfo(userService, model);

        CartHeader cartHeader = cartHeaderService.getCartHeaderByID(currentUser.getCustomerId());

        List<CartDetail> cartDetailList = cartDetailService.getCartDetailsByCartHeaderID(cartHeader.getId());
        
        model.addAttribute("user", currentUser);
        model.addAttribute("cartDetailList", cartDetailList);
        model.addAttribute("cartHeader", cartHeader);
        model.addAttribute("pageTitle", "Uraaa! Đặt hàng thành công!");
        
        LocalDateTime orderDate = LocalDateTime.now();
		Date dt = new Date();
		LocalDateTime dueDate = LocalDateTime.from(dt.toInstant().atZone(ZoneId.of("UTC"))).plusDays(10);
		short status = 1;
		BigDecimal freight = new BigDecimal(15000);
		BigDecimal subTotal = cartHeader.getTotalPrice();
		BigDecimal taxVat = subTotal.multiply(new BigDecimal(0.03));
		BigDecimal totalDue = subTotal.add(taxVat).add(freight);
        
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setAddress(address);
        saleOrder.setPhone(phone);
        saleOrder.setCustomer(currentUser);
        saleOrder.setOrderDate(orderDate);
        saleOrder.setDueDate(dueDate);
        saleOrder.setFreight(freight);
        saleOrder.setStatus(status);
        saleOrder.setSubTotal(subTotal);
        saleOrder.setTaxVat(taxVat);
        saleOrder.setTotalDue(totalDue);
        saleOrderService.addOrUpdateSaleOrder(saleOrder);
        
        List<SaleOrderDetail> saleOrderDetails = new ArrayList<SaleOrderDetail>();
        for (CartDetail c : cartDetailList) {
        	SaleOrderDetail s = new SaleOrderDetail(saleOrder, c.getLap(), c.getLapQty(), c.getLap().getPrice(),
        			c.getLap().getPrice().multiply(new BigDecimal(c.getLapQty())));
			saleOrderDetails.add(s);
			saleOrderDetailService.addOrUpdateSaleOrderDetail(s);
		}
        
        cartDetailService.deleteCartDetailByCartHeaderId(cartHeader.getId());
        cartHeader.setTotalPrice(new BigDecimal(0));
        cartHeader.setTotalQuantity(0);
        cartHeaderService.addOrUpdateCartHeader(cartHeader);
        
    	return "/view_customer/checkout_success";
    }
}
