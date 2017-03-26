package sendsmsservice;

import java.io.*;
import java.net.*;

import javax.jws.WebMethod;

import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import oracle.xml.parser.v2.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@WebService(serviceName = "SendSMSService", portName = "SendSMSServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class SendSMSService {
    
    private final static String endpoint = "https://rest.nexmo.com/sms/xml";
    private final static String charset = "UTF-8";
    
    public SendSMSService() {
        super();
    }
    
    public String processMessage(String to, String message) {
        
        String ret;
        
        Parameters messageParameters = new Parameters(to, message);
        ret = sendMessage(messageParameters);
        
        return ret;
    }

    @WebMethod(exclude = true)
    public String sendMessage(Parameters messageParameters) {
        
        try {
            String url = SendSMSService.endpoint + "?" + messageParameters.toString();
            
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", SendSMSService.charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "*/*");
            
            InputStream response = connection.getInputStream();
            
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int status = httpConnection.getResponseCode(); //FIXME Possibly throw exception
            
            if(status != HttpURLConnection.HTTP_OK) {
                return "HTTP error";
            }
            
            /*BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder responseString = new StringBuilder();
            
            String inputLine;
            
            while ((inputLine = rd.readLine()) != null)
                    responseString.append(inputLine);
            rd.close();*/
            
        }
        catch (IOException e) {
            return e.getMessage();
        }
        
        
        return "crl";
    }
}