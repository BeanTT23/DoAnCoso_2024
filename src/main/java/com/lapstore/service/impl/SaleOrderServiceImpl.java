package com.lapstore.service.impl;

import java.util.List;

import com.lapstore.entity.SaleOrder;
import com.lapstore.service.SaleOrderService;
import com.lapstore.repository.SaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {

	@Autowired
	private SaleOrderRepository saleOrderRepo;
	
	@Override
	public List<SaleOrder> getAllSaleOrder() {
		return saleOrderRepo.findAll();
	}

	@Override
	public List<SaleOrder> getSaleOrdersByCustomerID(int customerID) {
		return saleOrderRepo.getSaleOrdersByCustomerID(customerID);
	}

	@Override
	public SaleOrder getSaleOrderBySaleOrderID(int saleOrderID) {
		return saleOrderRepo.getSaleOrderBySaleOrderID(saleOrderID);
	}

	@Override
	public void addOrUpdateSaleOrder(SaleOrder saleOrder) {
		saleOrderRepo.save(saleOrder);
	}

	@Override
	public List<SaleOrder> getSaleOrderByStatus(short status) {
		return saleOrderRepo.getSaleOrderByStatus(status);
	}

	@Override
	public List<SaleOrder> getSaleOrderToday(int day, int month, int year) {
		return saleOrderRepo.getSaleOrderToday(day, month, year);
	}

	@Override
	public List<SaleOrder> getSaleOrderThisMonth(int month, int year) {
		return saleOrderRepo.getSaleOrderThisMonth(month, year);
	}

	@Override
	public List<SaleOrder> getSaleOrderThisWeek(String startOfWeek, String EndOfWeek) {
		return saleOrderRepo.getSaleOrderThisWeek(startOfWeek, EndOfWeek);
	}

	@Override
	public List<SaleOrder> getSaleOrderALikeByKeyWord(String keyword) {
		return saleOrderRepo.getSaleOrderALikeByKeyWord("%" + keyword + "%");
	}
}