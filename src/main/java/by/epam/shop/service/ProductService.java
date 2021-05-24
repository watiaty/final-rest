package by.epam.shop.service;

import by.epam.shop.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> findAll(Integer page);

    Product findById(Integer id);

    Page<Product> findByName(Integer page, String name);

//    List<Product> findByPrice(Integer page, Price price);

    Page<Product> findByCategoryId(Integer page, Integer id);

    boolean save(Product product);

    boolean deleteById(Integer id);
}
