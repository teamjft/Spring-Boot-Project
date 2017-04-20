package com.lms.dao.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.Book;
import com.lms.models.Category;

/**
 * Created by bhushan on 17/4/17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Iterable<Category> findByIdIn(Collection<Long> ids);
    Long countByName(String name);
    Category findByUuid(String uuid);
}
