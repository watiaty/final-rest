package by.epam.shop.soap;

import by.epam.shop.model.Price;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import java.util.ArrayList;
import java.util.List;

public class ResponseParser {

    public static List<Price> getPricesFromXML(String xml) {
        xml = xml.replace("ns2:", "");
        xml = xml.replace("SOAP-ENV:", "");
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return getPricesFromDocument(document);
    }

    private static List<Price> getPricesFromDocument(Document document) {
        List<Node> nodes = document.selectNodes("/Envelope/Body/GetPricesByProductResponse/PriceDetails");
        List<Price> prices = new ArrayList<>();
        for (Node node : nodes) {
            Price price = new Price();
            price.setId(Integer.parseInt(node.selectNodes("id").get(0).getStringValue()));
            price.setCurrency(node.selectNodes("currency").get(0).getStringValue());
            price.setValue(Integer.parseInt(node.selectNodes("value").get(0).getStringValue()));
            prices.add(price);
        }
        return prices;
    }
}
