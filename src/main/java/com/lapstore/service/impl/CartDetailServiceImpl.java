package com.lapstore.service.impl;

import java.util.List;

import com.lapstore.entity.CartDetail;
import com.lapstore.repository.CartDetailRepository;
import com.lapstore.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDetailServiceImpl implements CartDetailService {
	
	@Autowired
	private CartDetailRepository cartDetailRepository;

	@Override
	public List<CartDetail> getAllCartDetail() {
		return cartDetailRepository.findAll();
	}

	@Override
	public List<CartDetail> getCartDetailsByCartHeaderID(int cartID) {
		return cartDetailRepository.getCartDetailsByCartHeaderID(cartID);
	}

	@Override
	public List<CartDetail> getCartDetailsByLapID(int lapID) {
		return cartDetailRepository.getCartDetailsByLapID(lapID);
	}

	@Override
	public CartDetail getCartDetailByCartHeaderIdAndLapId(int cartID, int lapID) {
		return cartDetailRepository.getCartDetailByCartHeaderIdAndLapId(cartID, lapID);
	}

	@Override
	public void addOrUpdateCartDetail(CartDetail cartDetail) {
		cartDetailRepository.save(cartDetail);
	}

	@Override
	public void deleteCartDetailByCartHeaderId(int cartID) {
		cartDetailRepository.deleteCartDetailByCartHeaderId(cartID);
	}

	@Override
	public void deleteCartDetailByCartHeaderIdAndLapId(int cartID, int lapID) {
		cartDetailRepository.deleteCartDetailByCartHeaderIdAndLapId(cartID, lapID);
	}
}
