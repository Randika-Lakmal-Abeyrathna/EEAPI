package com.randikalakma.eeapi.repository;

import com.randikalakma.eeapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    int countByCategory(String category);

    void deleteCategoryByCategoryid(Integer id);
}
