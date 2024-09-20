package com.lapstore.repository;

import java.util.List;

import com.lapstore.entity.LapReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BagReviewRepository extends JpaRepository<LapReview, Integer> {
	@Query("from LapReview where bag_id = :bagID")
	List<LapReview> getBagReviewsByBagID(@Param("bagID") int bagID);
}
