package by.epam.shop.dto;

import by.epam.shop.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryToCategoryDTO {
    public List<CategoryDTO> convertList(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTO categoryDTO = convert(category);
            categoryDTOS.add(categoryDTO);
        }

        return categoryDTOS;
    }

    public CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        if (category.getParent() != null) {
            categoryDTO.setParent(convert(category.getParent()));
        }
        if (category.getChild() != null) {
            categoryDTO.setChild(convert(category.getChild()));
        }
        return categoryDTO;
    }
}
