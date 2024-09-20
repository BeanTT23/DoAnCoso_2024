package com.lapstore.service;

import java.util.List;

import com.lapstore.entity.CartHeader;
import com.lapstore.entity.CartDetail;

public interface CartHeaderService {
	List<CartHeader> getAllCartHeader();
	CartHeader getCartHeaderByID(int cartHeaderID);
	void addOrUpdateCartHeader(CartHeader cartHeader);
	void updateCartTotalPriceAndQuantity(int userID, List<CartDetail> cartDetails);
}