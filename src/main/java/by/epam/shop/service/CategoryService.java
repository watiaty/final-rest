package by.epam.shop.service;

import by.epam.shop.entity.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {
    Page<Category> findAll(Integer page);

    Category findById(Integer id);

    Page<Category> findByName(Integer page, String name);

    boolean save(Category category);

    boolean deleteById(Integer id);
}
