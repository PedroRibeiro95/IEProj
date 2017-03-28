package sendsmsservice;

import java.io.*;
import java.net.*;

import javax.jws.WebMethod;

import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import oracle.xml.parser.v2.*;
import oracle.xml.parser.v2.XMLDocument;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import sendsmsservice.exceptions.FailParsingResponseException;
import sendsmsservice.exceptions.FailSendingSMSException;
import sendsmsservice.exceptions.SMSServiceCallException;

@WebService(serviceName = "SendSMSService", portName = "SendSMSServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class SendSMSService {
    
    private final static String endpoint = "https://rest.nexmo.com/sms/xml";
    private final static String charset = "UTF-8";
    
    public SendSMSService() {
        super();
    }

    @WebMethod
    public boolean processMessage(@WebParam(name = "To")
        String to, @WebParam(name = "Message")
        String message) 
            throws FailSendingSMSException {
        try {
            Parameters messageParameters = new Parameters(to, message);
            String ret = sendMessage(messageParameters);
            boolean isOk = isOK(ret);
            
            return isOk;
        } catch (Exception e) {
            throw new FailSendingSMSException(e);
        }
    }

    @WebMethod(exclude = true)
    public String sendMessage(Parameters messageParameters) throws SMSServiceCallException {
        
        try {
            String url = SendSMSService.endpoint + "?" + messageParameters.toString();
            
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", SendSMSService.charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "*/*");
            
            InputStream response = connection.getInputStream();
            
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int status = httpConnection.getResponseCode();
            
            if(status != HttpURLConnection.HTTP_OK) {
                throw new SMSServiceCallException("Failed to connect");
            }
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
             
            StringBuilder responseBuilder = new StringBuilder();
            for(String inputLine = rd.readLine(); inputLine != null; inputLine = rd.readLine()) {
                responseBuilder.append(inputLine);
            }
 
            rd.close();
 
            return responseBuilder.toString();
            
        }
        catch (IOException e) {
            throw new SMSServiceCallException("Failed to connect");
        }
    }

    @WebMethod(exclude = true)
    public boolean isOK(String response) throws FailParsingResponseException {
        try {
            DOMParser domParser = new DOMParser();
            domParser.parse(new InputSource(new StringReader(response)));
            XMLDocument document = domParser.getDocument();
            
            XMLNode statusNode = (XMLNode) document.selectSingleNode("/mt-submission-response/messages/message/status");
            
            return statusNode.getText().equals("0");
           
        } catch(IOException e) {
           throw new FailParsingResponseException(e);
        } catch(SAXException e) {
           throw new FailParsingResponseException(e);
        } catch(XSLException e) {
           throw new FailParsingResponseException(e);
        }
    }
}