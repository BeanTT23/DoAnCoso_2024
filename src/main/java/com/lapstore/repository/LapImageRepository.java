package com.lapstore.repository;

import java.util.List;

import com.lapstore.entity.LapImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BagImageRepository extends JpaRepository<LapImage, Integer> {
	@Query("from LapImage where bag_id = :bagID")
	List<LapImage> getBagImagesByBagID(@Param("bagID") int bagID);
}
