package passwordgenerator;

import java.security.SecureRandom;

import java.util.Random;

import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(portName = "GeneratePasswordSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class GeneratePassword {
    
    private static final char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final int LENGTH = 8;
    
    public GeneratePassword() {
        super();
    }
    
    public String generatePassword() {
        Random random = new SecureRandom();
        char[] result = new char[LENGTH];
        for (int i = 0; i < result.length; i++) {
            // picks a random index out of character set > random character
            int randomCharIndex = random.nextInt(CHARSET_AZ_09.length);
            result[i] = CHARSET_AZ_09[randomCharIndex];
        }
        return new String(result);
    }
}
