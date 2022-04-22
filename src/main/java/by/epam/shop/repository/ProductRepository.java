package by.epam.shop.repository;

import by.epam.shop.entity.Category;
import by.epam.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByName(Pageable pageable, String name);

//    Page<Product> findByPrice(Pageable pageable, Price price);

    Page<Product> findAllByCategories(Pageable pageable, Category category);

    Page<Product> findAll(Pageable pageable);

}
