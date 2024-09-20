package com.lapstore.repository;

import java.time.LocalDate;
import java.util.List;

import com.lapstore.entity.LapCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BagCategoryRepository extends JpaRepository<LapCategory, Integer>{
	@Query("from LapCategory where name = :name")
	List<LapCategory> getBagCategoriesByName(@Param("name") String name);
	
	@Query("from LapCategory where name like :name")
	List<LapCategory> getBagCategoriesLikeName(@Param("name") String name);
	
	@Query("from LapCategory where import_date = :importDate")
	List<LapCategory> getBagCategoriesByImportDate(@Param("importDate") LocalDate importDate);
	
	@Query(value = "from LapCategory order by name asc")
	List<LapCategory> getBagCategoriesOrderByNameFromA2Z();
	
	@Query(value = "from LapCategory order by name desc")
	List<LapCategory> getBagCategoriesOrderByNameFromZ2A();
	
	@Query(value = "from LapCategory order by import_date desc")
	List<LapCategory> getBagCategoriesByNewestDate();

	@Query(value = "select top 5 * from bag_categories where "
			+ "name like :keyword or bag_category_id like :keyword", nativeQuery = true)
	List<LapCategory> searchBagCaterogyALikeByKeyword(@Param("keyword") String keyword);
 
//	@Query("select min(Bag.price) from BagCategory join Bag on BagCategory.bagCategoryId = Bag.bagCategory.bagCategoryId")
//	double getMinPriceOfBagCategory(@Param("bagCategoryId") int bagCategoryId);
}