package com.lapstore.repository;

import java.util.List;

import com.lapstore.entity.SaleOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleOrderDetailRepository extends JpaRepository<SaleOrderDetail, Integer> {
	@Query("from SaleOrderDetail where sale_order_id = :saleOrderID")
	List<SaleOrderDetail> getSaleOrderDetailsBySaleOrderID(@Param("saleOrderID") int saleOrderID);
	@Query("from SaleOrderDetail where lap_id = :lapID")
	List<SaleOrderDetail> getSaleOrderDetailsByLapID(@Param("lapID") int lapID);
	@Query("from SaleOrderDetail where sale_order_id = :saleOrderID AND lap_id = :lapID")
	SaleOrderDetail getSaleOrderDetailBySaleOrderIdAndLapId(@Param("saleOrderID") int saleOrderID, @Param("lapID") int lapID);
}
