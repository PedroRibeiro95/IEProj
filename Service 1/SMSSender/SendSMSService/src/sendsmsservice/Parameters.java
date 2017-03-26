package sendsmsservice;

import java.io.IOException;

import java.util.Properties;

public class Parameters {
    private static final String FROM_PROPERTY = "FormoNetwork";
    private static final String API_KEY_PROPERTY = "8d638ad3";
    private static final String API_SECRET = "84c01cb4f78c12d2";
    
    private String to;
    private String message;
    private String from;
    private String apiKey;
    private String apiSecret;
    
    
    public Parameters(String to, String message) {
        this.to = to;
        this.message = message;
        
        this.from = FROM_PROPERTY;
        this.apiKey = API_KEY_PROPERTY;
        this.apiSecret = API_SECRET;
    }
    
    @Override
    public String toString() {
        return "api_key="    + this.apiKey    + "&" +
               "api_secret=" + this.apiSecret + "&" +
               "to="         + this.to        + "&" +
               "from="       + this.from      + "&" +
               "text="       + this.message.replace(' ', '+');
    }
}