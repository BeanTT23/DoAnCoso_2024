package com.lapstore.repository;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;

import com.lapstore.entity.Lap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LapRepository extends JpaRepository<Lap, Integer>{
    @Query("from Lap where lapCategory.lapCategoryId = :lapCategoryId")
    List<Lap> findLapsListByLapCategoryId(@Param("lapCategoryId") int lapCategoryId);

	@Query(value = "select top 1 price from laps where lap_category_id = :cateID "
			+ "order by price desc", nativeQuery = true)
	BigDecimal getLapPriceByCateID(@Param("cateID") int cateID);

	@Query(value = "select lap_category_id from laps order by price asc", nativeQuery = true)
	LinkedHashSet<Integer> getLapCategoryIdOrderByPriceAsc();

	@Query(value = "select lap_category_id from laps order by price desc", nativeQuery = true)
	LinkedHashSet<Integer> getLapCategoryIdOrderByPriceDesc();

	@Query(value = "Select count(*) from laps where quantity > 0", nativeQuery = true)
	int countLap();

	@Query(value = "Select count(*) from laps where quantity < 1", nativeQuery = true)
	int countLapNotInStock();

	@Query(value = "select sum(quantity) from laps", nativeQuery = true)
	int sumQuantity();
}