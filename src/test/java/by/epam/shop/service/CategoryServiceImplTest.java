package by.epam.shop.service;

import by.epam.shop.entity.Category;
import by.epam.shop.repository.CategoryRepository;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryServiceImplTest{
    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void testSave() {
        Category category = new Category();
        boolean isCategoryCreated = categoryService.save(category);

        Assert.assertTrue(isCategoryCreated);

        Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
    }

    @Test
    public void testFindAll() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "food"));
        categories.add(new Category(2, "clothes"));
        Page<Category> page = new PageImpl<Category>(categories);
        Mockito.doReturn(page)
                .when(categoryRepository)
                .findAll(constructPageable(1));

        Page<Category> result = categoryService.findAll(1);

        Mockito.verify(categoryRepository, Mockito.times(1)).findAll(constructPageable(1));
    }

    @Test
    public void testFindById() {
        Mockito.doReturn(Optional.of(new Category()))
                .when(categoryRepository)
                .findById(1);

        Category result = categoryService.findById(1);

        Assert.assertNotNull(result);
        Mockito.verify(categoryRepository, Mockito.times(1)).findById(1);
    }

    @Test
    public void testFindByName() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "food"));
        categories.add(new Category(2, "clothes"));
        Page<Category> page = new PageImpl<Category>(categories);
        Mockito.doReturn(page)
                .when(categoryRepository)
                .findByName(constructPageable(1), "food");

        Page<Category> result = categoryService.findByName(1, "food");

        Mockito.verify(categoryRepository, Mockito.times(1)).findByName(constructPageable(1), "food");
    }

    @Test
    public void testDeleteById() {
        boolean isCategoryDelete = categoryService.deleteById(1);

        Assert.assertTrue(isCategoryDelete);

        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(1);
    }
}