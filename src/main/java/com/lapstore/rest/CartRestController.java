package com.lapstore.rest;

import com.lapstore.entity.CartDetail;
import com.lapstore.entity.Lap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.lapstore.service.impl.LapServiceImpl;
import com.lapstore.service.impl.CartDetailServiceImpl;
import com.lapstore.service.impl.CartHeaderServiceImpl;

@RestController
@RequestMapping("/api_cart")
public class CartRestController {

	@Autowired
	private CartDetailServiceImpl cartDetailServiceImpl;

	@Autowired
	private CartHeaderServiceImpl cartHeaderServiceImpl;

	@Autowired
	private LapServiceImpl lapServiceImpl;

	@PutMapping("/update")
	public String changeCartOrderQuantity(
			@RequestParam int lapID, @RequestParam int userID, @RequestParam int quantity
	) {

		Lap lap = lapServiceImpl.getLapByID(lapID);
		int quantityInStock = lap.getQuantity() - quantity;
//		Order quantity cant greater than quantity in stock
		if (quantityInStock < 0) return "failed";

		CartDetail cartDetail = cartDetailServiceImpl.getCartDetailByCartHeaderIdAndLapId(userID, lapID);
		cartDetail.setLapQty(quantity);

		cartDetailServiceImpl.addOrUpdateCartDetail(cartDetail);

		cartHeaderServiceImpl.updateCartTotalPriceAndQuantity(userID,
				cartDetailServiceImpl.getCartDetailsByCartHeaderID(userID));

		return "success";
	}

	@DeleteMapping("/delete")
	public String deleteCartOrderItem(@RequestParam int lapID, @RequestParam int userID) {
		
		cartDetailServiceImpl.deleteCartDetailByCartHeaderIdAndLapId(userID, lapID);
		
		cartHeaderServiceImpl.updateCartTotalPriceAndQuantity(userID,
				cartDetailServiceImpl.getCartDetailsByCartHeaderID(userID));
		
		return "success";
	}
}