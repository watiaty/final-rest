package by.epam.shop.service;

import by.epam.shop.entity.Product;
import by.epam.shop.repository.CategoryRepository;
import by.epam.shop.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private static final Integer ENTITY_ON_PAGE = 2;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Product> findAll(Integer page) {
        return productRepository.findAll(constructPageable(page));
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> result = productRepository.findById(id);

        Product product = null;
        if (result.isPresent()) {
            product = result.get();
        }

        return product;
    }

    @Override
    public Page<Product> findByName(Integer page, String name) {
        return productRepository.findByName(constructPageable(page), name);
    }

//    @Override
//    public List<Product> findByPrice(Price price) {
//        return productRepository.findByPrice(price);
//    }

    @Override
    public Page<Product> findByCategoryId(Integer page, Integer id) {
        return productRepository.findAllByCategories(constructPageable(page), categoryRepository.findById(id).get());
    }

    @Override
    public boolean save(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        productRepository.deleteById(id);
        return true;
    }

    public static Pageable constructPageable(Integer pageNumber) {
        return PageRequest.of(pageNumber, ENTITY_ON_PAGE);
    }
}
