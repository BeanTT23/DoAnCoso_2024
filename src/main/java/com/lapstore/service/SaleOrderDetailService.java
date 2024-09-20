package com.lapstore.service;

import java.util.List;

import com.lapstore.entity.SaleOrderDetail;

public interface SaleOrderDetailService {
	List<SaleOrderDetail> getAllSaleOrderDetail();
	List<SaleOrderDetail> getSaleOrderDetailsBySaleOrderID(int saleOrderID);
	List<SaleOrderDetail> getSaleOrderDetailsByLapID(int lapID);
	SaleOrderDetail getSaleOrderDetailBySaleOrderIdAndLapId(int saleOrderID, int lapID);
	void addOrUpdateSaleOrderDetail(SaleOrderDetail saleOrderDetail);
}
