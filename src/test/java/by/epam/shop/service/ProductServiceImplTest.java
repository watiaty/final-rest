package by.epam.shop.service;

import by.epam.shop.entity.Category;
import by.epam.shop.entity.Product;
import by.epam.shop.repository.ProductRepository;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.shop.service.ProductServiceImpl.constructPageable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testFindAll() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "meat"));
        products.add(new Product(2, "milk"));
        products.add(new Product(3, "bread"));
        Page<Product> page = new PageImpl<>(products);
        Mockito.doReturn(page)
                .when(productRepository)
                .findAll(constructPageable(1));

        Page<Product> result = productService.findAll(1);

        Mockito.verify(productRepository, Mockito.times(1)).findAll(constructPageable(1));
    }

    @Test
    public void testFindById() {
        Mockito.doReturn(Optional.of(new Product()))
                .when(productRepository)
                .findById(1);

        Product result = productService.findById(1);

        Assert.assertNotNull(result);
        Mockito.verify(productRepository, Mockito.times(1)).findById(1);
    }

    @Test
    public void testFindByName() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "meat"));
        Page<Product> page = new PageImpl<>(products);
        Mockito.doReturn(page)
                .when(productRepository)
                .findByName(constructPageable(1), "meat");

        Page<Product> result = productService.findByName(1, "meat");

        Mockito.verify(productRepository, Mockito.times(1)).findByName(constructPageable(1), "meat");
    }

    @Test
    public void testFindByCategoryId() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "meat"));
        products.add(new Product(2, "milk"));
        products.add(new Product(3, "bread"));
        Page<Product> page = new PageImpl<>(products);
        Mockito.doReturn(page)
                .when(productRepository)
                .findAllByCategories(constructPageable(1), new Category(1, "food"));

        Page<Product> result = productService.findByCategoryId(1, 1);

        Mockito.verify(productRepository, Mockito.times(1)).findAllByCategories(constructPageable(1), new Category(1, "food"));
    }

    @Test
    public void testSave() {
        Product product = new Product();
        boolean isProductCreated = productService.save(product);

        Assert.assertTrue(isProductCreated);

        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    public void testDeleteById() {
        boolean isProductDelete = productService.deleteById(1);

        Assert.assertTrue(isProductDelete);

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1);
    }
}