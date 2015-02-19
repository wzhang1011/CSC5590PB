import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamConsumer {
    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("wqPy4zwicJNQqAReAqUDIEpmC");
        cb.setOAuthConsumerSecret("bZYAt89N2uyKNXDMp4WY8wxjevU2Euy72OFfaSuUmbrQeyG7rp");
        cb.setOAuthAccessToken("2213604889-25nd0wPLgfbvfkQAEHK0fSVrlmoeeg1oqOxqWvT");
        cb.setOAuthAccessTokenSecret("5hHXD33ROAznR7Va3qUzw340Wb88taXnckVYImrKzHK7D");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();
                
                // gets Username
                String username = status.getUser().getScreenName();
                String profileLocation = user.getLocation();
                long tweetId = status.getId(); 
                String content = status.getText();
                System.out.println(username+"\n"+tweetId+"\n"+profileLocation+"\n"+content);
                String text = username+","+tweetId+","+profileLocation+","+content;
                try {

                	PrintWriter pw = new PrintWriter(new FileWriter("tweets2.txt", true));
                	pw.println(text);
                	pw.close();
                  } catch ( IOException e ) {
                     e.printStackTrace();
                  }
            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        twitterStream.addListener(listener);
        twitterStream.sample(); 

    }
}