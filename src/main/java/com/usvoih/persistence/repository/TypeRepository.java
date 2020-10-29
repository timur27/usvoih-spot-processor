package com.usvoih.persistence.repository;

import com.usvoih.dto.Category;
import com.usvoih.persistence.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByCategoryAndSubcategory(Category category, String subcategory);
}
