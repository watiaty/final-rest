package by.epam.shop.repository;

import by.epam.shop.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Page<Category> findByName(Pageable pageable, String name);

    Page<Category> findAll(Pageable pageable);
}
