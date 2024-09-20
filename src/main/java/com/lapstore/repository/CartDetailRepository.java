package com.lapstore.repository;

import java.util.List;

import com.lapstore.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
	@Query("from CartDetail where cart_header_id = :cartID")
	List<CartDetail> getCartDetailsByCartHeaderID(@Param("cartID") int cartID);
	
	@Query("from CartDetail where lap_id = :lapID")
	List<CartDetail> getCartDetailsByLapID(@Param("lapID") int lapID);
	
	@Query("from CartDetail where cart_header_id = :cartID AND lap_id = :lapID")
	CartDetail getCartDetailByCartHeaderIdAndLapId(@Param("cartID") int cartID, @Param("lapID") int lapID);

	@Modifying
	@Transactional
	@Query("delete from CartDetail where cart_header_id = :cartID")
	void deleteCartDetailByCartHeaderId(@Param("cartID") int cartID);
	
	@Modifying
	@Transactional
	@Query("delete from CartDetail where cart_header_id = :cartID AND lap_id = :lapID")
	void deleteCartDetailByCartHeaderIdAndLapId(@Param("cartID") int cartID, @Param("lapID") int lapID);
}