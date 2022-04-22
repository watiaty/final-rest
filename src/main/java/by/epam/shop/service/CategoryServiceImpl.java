package by.epam.shop.service;

import by.epam.shop.entity.Category;
import by.epam.shop.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private static final Integer ENTITY_ON_PAGE = 2;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> findAll(Integer page) {
        return categoryRepository.findAll(constructPageable(page));
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> result = categoryRepository.findById(id);

        Category category = null;
        if (result.isPresent()) {
            category = result.get();
        }

        return category;
    }

    @Override
    public Page<Category> findByName(Integer page, String name) {
        return categoryRepository.findByName(constructPageable(page), name);
    }

    @Override
    public boolean save(Category category) {
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        categoryRepository.deleteById(id);
        return true;
    }

    public final Pageable constructPageable(Integer pageNumber) {
        return PageRequest.of(pageNumber, ENTITY_ON_PAGE);
    }
}
