package com.lapstore.repository;

import java.util.List;

import com.lapstore.entity.LapReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LapReviewRepository extends JpaRepository<LapReview, Integer> {
	@Query("from LapReview where lap_id = :lapID")
	List<LapReview> getLapReviewsByLapID(@Param("lapID") int lapID);
}
