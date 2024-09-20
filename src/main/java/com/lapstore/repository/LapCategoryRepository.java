package com.lapstore.repository;

import java.time.LocalDate;
import java.util.List;

import com.lapstore.entity.LapCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LapCategoryRepository extends JpaRepository<LapCategory, Integer>{
	@Query("from LapCategory where name = :name")
	List<LapCategory> getLapCategoriesByName(@Param("name") String name);
	
	@Query("from LapCategory where name like :name")
	List<LapCategory> getLapCategoriesLikeName(@Param("name") String name);
	
	@Query("from LapCategory where import_date = :importDate")
	List<LapCategory> getLapCategoriesByImportDate(@Param("importDate") LocalDate importDate);
	
	@Query(value = "from LapCategory order by name asc")
	List<LapCategory> getLapCategoriesOrderByNameFromA2Z();
	
	@Query(value = "from LapCategory order by name desc")
	List<LapCategory> getLapCategoriesOrderByNameFromZ2A();
	
	@Query(value = "from LapCategory order by import_date desc")
	List<LapCategory> getLapCategoriesByNewestDate();

	@Query(value = "select top 5 * from lap_categories where "
			+ "name like :keyword or lap_category_id like :keyword", nativeQuery = true)
	List<LapCategory> searchLapCaterogyALikeByKeyword(@Param("keyword") String keyword);
 
//	@Query("select min(Lap.price) from LapCategory join Lap on LapCategory.lapCategoryId = Lap.lapCategory.lapCategoryId")
//	double getMinPriceOfLapCategory(@Param("lapCategoryId") int lapCategoryId);
}