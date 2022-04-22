package by.epam.shop.dto;

import by.epam.shop.entity.Category;
import by.epam.shop.entity.Product;
import by.epam.shop.model.Price;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDTOToProduct {
    private final PriceDTOToPrice priceDTOToPrice;
    private final CategoryDTOToCategory categoryDTOToCategory;

    public ProductDTOToProduct(PriceDTOToPrice priceDTOToPrice, CategoryDTOToCategory categoryDTOToCategory) {
        this.priceDTOToPrice = priceDTOToPrice;
        this.categoryDTOToCategory = categoryDTOToCategory;
    }

    public Product convert(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        List<Price> priceList = new ArrayList<>();
        for (PriceDTO priceDTO : productDTO.getPriceDTOS()) {
            priceList.add(priceDTOToPrice.convert(priceDTO));
        }
        product.setPrice(priceList);
        List<Category> categoryList = new ArrayList<>();
        for (CategoryDTO categoryDTO : productDTO.getCategoryDTOS()) {
            categoryList.add(categoryDTOToCategory.convert(categoryDTO));
        }
        product.setCategories(categoryList);
        return product;
    }
}
