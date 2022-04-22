package by.epam.shop.dto;

import by.epam.shop.entity.Product;
import by.epam.shop.soap.SOAPClientSAAJ;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductDTO {
    private final PriceToPriceDTO priceToPriceDTO;
    private final CategoryToCategoryDTO categoryToCategoryDTO;
    private final SOAPClientSAAJ soapClientSAAJ;

    public ProductToProductDTO(PriceToPriceDTO priceToPriceDTO, CategoryToCategoryDTO categoryToCategoryDTO, SOAPClientSAAJ soapClientSAAJ) {
        this.priceToPriceDTO = priceToPriceDTO;
        this.categoryToCategoryDTO = categoryToCategoryDTO;
        this.soapClientSAAJ = soapClientSAAJ;
    }

    public ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPriceDTOS(priceToPriceDTO.convertList(soapClientSAAJ.getPricesByProduct(product)));
        productDTO.setCategoryDTOS(categoryToCategoryDTO.convertList(product.getCategories()));
        return productDTO;
    }
}
