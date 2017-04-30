package paymentprocessingservice;

import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(name = "PaymentProcessing", serviceName = "PaymentProcessingService", portName = "PaymentProcessingSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class PaymentProcessingWS {
    public PaymentProcessingWS() {
        super();
    }

    @WebMethod
    public Boolean processPayment(@WebParam(name = "creditCardNumber")
        String cc, @WebParam(name = "value")
        double value){
        Random r = new Random();
        int Low = 1;
        int High = 5;
        int Result = r.nextInt(High-Low) + Low;
        if(Result >= 4)
            return false;
        else
            return true;
    }
}
