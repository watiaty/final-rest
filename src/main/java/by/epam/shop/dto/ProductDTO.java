package by.epam.shop.dto;

import java.util.List;

public class ProductDTO {
    private Integer id;
    private String name;
    private List<PriceDTO> priceDTOS;
    private List<CategoryDTO> categoryDTOS;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PriceDTO> getPriceDTOS() {
        return priceDTOS;
    }

    public void setPriceDTOS(List<PriceDTO> priceDTOS) {
        this.priceDTOS = priceDTOS;
    }

    public List<CategoryDTO> getCategoryDTOS() {
        return categoryDTOS;
    }

    public void setCategoryDTOS(List<CategoryDTO> categoryDTOS) {
        this.categoryDTOS = categoryDTOS;
    }

    @Override
    public String toString() {
        return name + " ; " + priceDTOS.get(0) + " ; " + categoryDTOS.get(0);
    }
}
