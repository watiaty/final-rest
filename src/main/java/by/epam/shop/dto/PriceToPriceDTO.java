package by.epam.shop.dto;

import by.epam.shop.model.Price;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PriceToPriceDTO {

    public PriceDTO convert(Price price) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(price.getId());
        priceDTO.setValue(price.getValue());
        priceDTO.setCurrency(price.getCurrency());
        return priceDTO;
    }

    public List<PriceDTO> convertList(List<Price> prices) {
        List<PriceDTO> priceDTOS = new ArrayList<>();
        for (Price price : prices) {
            PriceDTO priceDTO = new PriceDTO();
            priceDTO.setId(price.getId());
            priceDTO.setCurrency(price.getCurrency());
            priceDTO.setValue(price.getValue());
            priceDTOS.add(priceDTO);
        }
        return priceDTOS;
    }
}
