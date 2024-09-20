package com.lapstore.repository;

import java.util.List;

import com.lapstore.entity.LapImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LapImageRepository extends JpaRepository<LapImage, Integer> {
	@Query("from LapImage where lap_id = :lapID")
	List<LapImage> getLapImagesByLapID(@Param("lapID") int lapID);
}
