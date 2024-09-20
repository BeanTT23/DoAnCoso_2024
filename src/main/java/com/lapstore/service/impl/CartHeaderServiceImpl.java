package com.lapstore.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.lapstore.entity.CartDetail;
import com.lapstore.entity.CartHeader;
import com.lapstore.service.CartHeaderService;
import com.lapstore.repository.CartHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartHeaderServiceImpl implements CartHeaderService {

	@Autowired
	private CartHeaderRepository cartHeaderRepo;

	@Override
	public List<CartHeader> getAllCartHeader() {
		return cartHeaderRepo.findAll();
	}

	@Override
	public CartHeader getCartHeaderByID(int cartHeaderID) {
		Optional<CartHeader> result = cartHeaderRepo.findById(cartHeaderID);
		CartHeader cartHeader = null;
		if (result.isPresent()) {
			cartHeader = result.get();
		} else {
			throw new RuntimeException("Did not find cart header id - " + cartHeaderID);
		}
		return cartHeader;
	}

	@Override
	public void addOrUpdateCartHeader(CartHeader cartHeader) {
		cartHeaderRepo.save(cartHeader);
	}

	@Override
	public void updateCartTotalPriceAndQuantity(int userID, List<CartDetail> cartDetails) {

		CartHeader cartHeader = getCartHeaderByID(userID);

		int totalQuantity = 0;
		BigDecimal totalPrice = new BigDecimal(0);

		for (CartDetail cartDetail : cartDetails) {
			totalPrice = totalPrice
					.add(cartDetail.getLap().getPrice().multiply(new BigDecimal(cartDetail.getLapQty())));
			totalQuantity += cartDetail.getLapQty();
		}
			
		cartHeader.setTotalPrice(totalPrice);
		cartHeader.setTotalQuantity(totalQuantity);
		
		addOrUpdateCartHeader(cartHeader);
	}
}