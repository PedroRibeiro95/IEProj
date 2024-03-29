import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class TwitterAPI {
    
    private static final String consumerKey = "AyfPrZ2fZvuPgMbUNZbKGK7ep";
    private static final String consumerSecret = "ILXFeYCCt4r3vBy8A8F4Nf3df0I50vHRpZwr88sdQFsU8mEloW";
    private static final String accessToken = "862727913585737730-ktGh3MsQrPVEZ78jplYptT0Eb9DgON1";
    private static final String accessTokenSecret = "4RP1zjcxLucWKLN425WikIw6ylNqzBeDb0kvi4zWXR47o";

    public static void main(String[] args) {
        writeTweet(10);
    }

    public static boolean writeTweet(int itemid) {

        try {
            //Instantiate a reusable factory
            TwitterFactory twitterFactory = new TwitterFactory();

            //Instantiate a new Twitter instance
            Twitter twitter = twitterFactory.getInstance();

            //Setup OAuth consumer credentials
            twitter.setOAuthConsumer(consumerKey, consumerSecret);

            //Setup OAuth access token
            twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

            //Instantiate and initialize a new twitter status update
            StatusUpdate statusUpdate = new StatusUpdate("This item is being bought with frequence! Buy yours before it runs out of stock!");

            //Attach corresponding photo
            statusUpdate.setMedia("Item:", new URL("http://c3.quickcachr.fotos.sapo.pt/i/b6004fe55/6812833_er5l5.jpeg").openStream());

            //Tweet or update status
            Status status = twitter.updateStatus(statusUpdate);

            return true;
        }
        catch (TwitterException e) {
            return false;
        }
        catch (IOException e) {
            return false;
        }
    }
}
