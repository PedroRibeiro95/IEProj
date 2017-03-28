package verifyaddress;

import exceptions.FailParsingResponseException;

import exceptions.FailVerifyingAddressException;

import exceptions.CTTServiceCallException;

import java.io.*;
import java.net.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import oracle.xml.parser.v2.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


@WebService(portName = "VerifyAddressCTTSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class VerifyAddressCTT {
    
    private final static String endpoint = "http://www.ctt.pt/pdcp/xml_pdcp";
    private final static String charset = "UTF-8";
    
    public VerifyAddressCTT() {
        super();
    }

    @WebMethod
    public boolean verifiedAddress(@WebParam(name = "Address")
        String fullAddress) 
            throws FailVerifyingAddressException {
        
        try {
            String addressSplit[] = fullAddress.split(",");
            
            Address address = new Address(addressSplit[0], addressSplit[1], addressSplit[2], addressSplit[3],
                                            addressSplit[4], addressSplit[5]);
            
            String response = invokeCTTService(address);
            
            boolean verification = verifyResponse(response);
            
            return verification;
        } catch (Exception e) {
            throw new FailVerifyingAddressException(e);
        }
    }

    @WebMethod(exclude = true)
    public String invokeCTTService(Address address) throws CTTServiceCallException {
        
        String url = VerifyAddressCTT.endpoint + "?" + address.toString();
        
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", VerifyAddressCTT.charset);
            InputStream response = connection.getInputStream();
            
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int status = httpConnection.getResponseCode();
            
            if(status != HttpURLConnection.HTTP_OK) {
                throw new CTTServiceCallException("Failed to connect");
            }
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder responseString = new StringBuilder();
            
            String inputLine;
            
            while ((inputLine = rd.readLine()) != null)
                    responseString.append(inputLine);
            rd.close();
            
            return responseString.toString();
            
        } catch (IOException e) {
            throw new CTTServiceCallException(e);
        }
    }
    
    @WebMethod(exclude = true)
    public boolean verifyResponse(String response) throws FailParsingResponseException {
        
        XMLDocument document;
        
        try {
            DOMParser domParser = new DOMParser();
            domParser.parse(new InputSource(new StringReader(response)));
            document = domParser.getDocument();
            
            return document.getDocumentElement().getTagName().equals("OK");
        }
        catch (IOException e) {
            throw new FailParsingResponseException(e);
        }
        catch (SAXException e) {
            throw new FailParsingResponseException(e);
        }
    }
}