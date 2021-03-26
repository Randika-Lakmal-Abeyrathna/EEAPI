package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer> {

    int countBySubCategoryName(String subCategory);

    Optional<SubCategory> findSubCategoryBySubCategoryName(String subCategory);

    List<SubCategory> findBySubCategoryNameIgnoreCaseContaining(String subCategory);

    void deleteSubcategoryByIdsubCategory(Integer id);
}
