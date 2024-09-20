package com.lapstore.service;

import java.util.List;

import com.lapstore.entity.CartDetail;

public interface CartDetailService {
	List<CartDetail> getAllCartDetail();
	List<CartDetail> getCartDetailsByCartHeaderID(int cartID);
	List<CartDetail> getCartDetailsByLapID(int lapID);
	CartDetail getCartDetailByCartHeaderIdAndLapId(int cartID, int lapID);
	void addOrUpdateCartDetail(CartDetail cartDetail);
	void deleteCartDetailByCartHeaderId(int cartID);
	void deleteCartDetailByCartHeaderIdAndLapId(int cartID, int lapID);
}