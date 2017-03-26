package externalctt;

import java.io.*;
import java.net.*;

import javax.jws.WebMethod;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import oracle.xml.parser.v2.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@WebService(serviceName = "ExternalCTTService", portName = "ExternalCTTServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class ExternalCTTService {
    
    private final static String endpoint = "http://www.ctt.pt/pdcp/xml_pdcp";
    private final static String charset = "UTF-8";
    
    public ExternalCTTService() {
        super();
    }
    
    public boolean verifiedAddress(String fullAddress) {
        
        String addressSplit[] = fullAddress.split(",");
        
        Address address = new Address(addressSplit[0], addressSplit[1], addressSplit[2], addressSplit[3],
                                        addressSplit[4], addressSplit[5]);
        
        String response = invokeCTTService(address);
        
        boolean verification = verifyResponse(response);
        
        return verification;
    }

    @WebMethod(exclude = true)
    public String invokeCTTService(Address address) {
        
        String url = ExternalCTTService.endpoint + "?" + address.toString();
        
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", ExternalCTTService.charset);
            InputStream response = connection.getInputStream();
            
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int status = httpConnection.getResponseCode(); //FIXME Possibly throw exception
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder responseString = new StringBuilder();
            
            String inputLine;
            
            while ((inputLine = rd.readLine()) != null)
                    responseString.append(inputLine);
            rd.close();
            
            return responseString.toString();
        }
        catch (Exception e) {
            
        }
        
        return null;
    }

    @WebMethod(exclude = true)
    public boolean verifyResponse(String response) {
        
        XMLDocument document;
        
        try {
            DOMParser domParser = new DOMParser();
            domParser.parse(new InputSource(new StringReader(response)));
            document = domParser.getDocument();
            
            return document.getDocumentElement().getTagName().equals("OK");
        }
        catch (IOException e) {
            //FIXME
        }
        catch (SAXException e) {
            //FIXME
        }
        
        return false;
    }
}