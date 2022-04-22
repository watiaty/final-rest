package by.epam.shop.dto;

import by.epam.shop.model.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceDTOToPrice {
    public Price convert(PriceDTO priceDTO) {
        Price price = new Price();
        price.setId(priceDTO.getId());
        price.setCurrency(priceDTO.getCurrency());
        price.setValue(priceDTO.getValue());
        return price;
    }
}
