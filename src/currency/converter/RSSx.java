/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package currency.converter;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.*;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Jeremy
 */
public class RSSx {
        public static void main(String[] args) {
        String URLstr = "http://themoneyconverter.com/rss-feed/";
        String FromCurrency = "USD";
        SyndFeed feed = null;
        try {
            URL feedUrl = new URL(URLstr+FromCurrency+"/rss.xml");

            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(feedUrl));

            System.out.println(feed);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }
        
        if (feed != null) {
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
                String title = entry.getTitle();
                String descr = entry.getDescription().getValue();
                
                System.out.println("Title: "+title+"\nDescription: "+descr);
            }
        } else {
            System.out.println("RSS Feed unavailable.");
        }
    }
}
