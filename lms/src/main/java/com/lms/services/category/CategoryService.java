package com.lms.services.category;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lms.models.Book;
import com.lms.models.Category;

/**
 * Created by bhushan on 17/4/17.
 */
public interface CategoryService {
    Category get(Long id);
    List<Category> getAll();
    Category create(Category category);
    void delete(Long id);
    void update(Category category);
    Iterable<Category>  getCategoryByIds(List<Long> ids);
    Long count();
    Page<Category> getPageRequest(Integer pageNumber);
    Long getCountByName(String name);
    Category findByUuid(String uuid);
}
