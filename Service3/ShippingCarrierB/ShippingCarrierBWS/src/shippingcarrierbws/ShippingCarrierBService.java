package shippingcarrierbws;

import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(serviceName = "ShippingCarrierBService", portName = "ShippingCarrierBServiceSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class ShippingCarrierBService {
    public ShippingCarrierBService() {
        super();
    }
    @WebMethod 
     public double calculateShippingRate (@WebParam(name="boutiqueAdress") String boutiqueAddress, @WebParam(name="clientAddress") String clientAddress, @WebParam(name="shippingTimes") int shippingTimes) {
            Random r = new Random();
            int Low = 50;
            int High = 101;
            int Result = r.nextInt(High-Low) + Low;
            if (shippingTimes <= 0)
                   shippingTimes=1;
            return (double) Result/shippingTimes; //quanto maior é o shippingTime mais barato é
        }
}
