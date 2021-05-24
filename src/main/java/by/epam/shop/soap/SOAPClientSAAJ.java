package by.epam.shop.soap;

import by.epam.shop.model.Price;
import by.epam.shop.entity.Product;
import org.springframework.stereotype.Component;

import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class SOAPClientSAAJ {

    private static void createSoapEnvelope(SOAPMessage soapMessage, Product product) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "";
        String myNamespaceURI = "http://localhost:8081";

        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("GetPricesByProductRequest", myNamespace);

        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("ProductDetails", myNamespace);
        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("id", myNamespace);

        soapBodyElem2.addTextNode(product.getId() + "");
        SOAPElement soapBodyElem3 = soapBodyElem1.addChildElement("name", myNamespace);
        soapBodyElem3.addTextNode(product.getName());
    }

    public List<Price> getPricesByProduct(Product product) {
        List<Price> prices = new ArrayList<>();
        try {
            String soapEndpointUrl = "http://localhost:8081/ws/GetPriceByRangeRequest";
            String soapAction = "http://localhost:8081/";

            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, product), soapEndpointUrl);

            // Print the SOAP Response
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            soapResponse.writeTo(stream);
            prices = ResponseParser.getPricesFromXML(new String(stream.toByteArray()));
            soapConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prices;
    }

    private static SOAPMessage createSOAPRequest(String soapAction, Product product) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage, product);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        return soapMessage;
    }

}
