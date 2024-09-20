package com.lapstore.service.impl;

import java.util.List;

import com.lapstore.entity.SaleOrderDetail;
import com.lapstore.service.SaleOrderDetailService;
import com.lapstore.repository.SaleOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleOrderDetailServiceImpl implements SaleOrderDetailService {
	
	@Autowired
	private SaleOrderDetailRepository saleOrderDetailRepo;

	@Override
	public List<SaleOrderDetail> getAllSaleOrderDetail() {
		return saleOrderDetailRepo.findAll();
	}

	@Override
	public List<SaleOrderDetail> getSaleOrderDetailsBySaleOrderID(int saleOrderID) {
		return saleOrderDetailRepo.getSaleOrderDetailsBySaleOrderID(saleOrderID);
	}

	@Override
	public List<SaleOrderDetail> getSaleOrderDetailsByLapID(int lapID) {
		return saleOrderDetailRepo.getSaleOrderDetailsByLapID(lapID);
	}

	@Override
	public SaleOrderDetail getSaleOrderDetailBySaleOrderIdAndLapId(int saleOrderID, int lapID) {
		return saleOrderDetailRepo.getSaleOrderDetailBySaleOrderIdAndLapId(saleOrderID, lapID);
	}

	@Override
	public void addOrUpdateSaleOrderDetail(SaleOrderDetail saleOrderDetail) {
		saleOrderDetailRepo.save(saleOrderDetail);
	}
	
}
