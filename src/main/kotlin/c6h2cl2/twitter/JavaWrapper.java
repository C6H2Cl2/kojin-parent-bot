package c6h2cl2.twitter;

import twitter4j.StatusListener;
import twitter4j.TwitterStream;

/**
 * @author C6H2Cl2
 */
public class JavaWrapper {
    public static void addStatusListener(TwitterStream stream, StatusListener listener){
        stream.addListener(listener);
    }
}
