package com.lms.services.category;

import static com.lms.utils.constants.Constant.PAGE_SIZE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lms.models.Category;
import com.lms.dao.repository.CategoryRepository;

/**
 * Created by bhushan on 17/4/17.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category get(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public  Iterable<Category>  getCategoryByIds(List<Long> ids) {
      return categoryRepository.findByIdIn(ids);
    }

    @Override
    public Long count() {
        return categoryRepository.count();
    }

    public Page<Category> getPageRequest(Integer pageNumber) {
        PageRequest request =
                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
        return categoryRepository.findAll(request);
    }

    public Long getCountByName(String name) {
        return categoryRepository.countByName(name);
    }

    @Override
    public Category findByUuid(String uuid) {
        return categoryRepository.findByUuid(uuid);
    }
}
