package by.epam.shop.dto;

import by.epam.shop.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOToCategory {
    public Category convert(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setParent(convert(categoryDTO.getParent()));
        category.setChild(convert(categoryDTO.getChild()));
        return category;
    }
}
