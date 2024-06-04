package com.example.GoodsCollectionPoint.repositories;

import com.example.GoodsCollectionPoint.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
