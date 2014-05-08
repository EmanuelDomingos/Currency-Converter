/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package currency.converter;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import flexjson.JSONSerializer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
/**
 *
 * @author jeremy.shields
 */
public class CurrencyConverter {

    /**
     * @param args the command line arguments
     */

    public String[] glo = new String[100];
    public static void main(String[] args) {
       JFrame guiFrame = new JFrame();
       
       
       
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("USD Converter");
        guiFrame.setSize(300,150);
        
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        
        
        final JPanel comboPanel = new JPanel();
         String[] abrv = {"AED", "ARS", "AUD", "AWG", 
                                                "BAM", "BBD", "BDT", "BGN", "BHD",
                                                "BMD", "BOB", "BRL", "BSD", "CAD",
                                                "CHF", "CLP", "CNY", "COP", "CZK",
                                                "DKK", "DOP", "EGP", "EUR", "FJD",
                                                "GBP", "GHS", "GMD", "GTQ", "HKD",
                                                "HRK", "HUF", "IDR", "ILS", "INR",
                                                "ISK", "JMD" , "JOD", "JPY", "KES",
                                                "LAK", "LBP", "LKR", "LTL", "MAD",
                                                "MDL", "MGA", "MKD", "MUR", "MXN",
                                                "MYR", "NAD", "NGN", "NOK", "NPR",
                                                "NZD", "OMR", "PAB", "PEN", "PHP",
                                                "PKR", "PLN", "PYG", "QAR", "RON",
                                                "RSD", "RUB", "SAR", "SCR", "SEK",
                                                "SGD", "SYP", "THB", "TND", "TRY",
                                                "TWD", "UAH", "UGX", "UYU", "VEF",
                                                "VND", "XAF", "XCD", "XOF", "XPF",
                                                "ZAR"};
        
        JLabel comboLb1 = new JLabel("Currency:");
        
        JComboBox currency = new JComboBox(abrv);
        
        JTextField amount = new JTextField();
        
        
        //currency.addActionListener(this);
        
        comboPanel.add(comboLb1);
        comboPanel.add(currency);
        comboPanel.add(amount);
        
        JButton convert = new JButton("Convert");
        JButton Update = new JButton("Update");
        JButton About = new JButton("About");
        
        final JPanel listPanel = new JPanel();
        listPanel.setVisible(false);
        JLabel listLbl = new JLabel("Conversion:");
        DefaultListModel number;
        number = new DefaultListModel();
        JList vegs = new JList(number);
        vegs.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        
        listPanel.add(listLbl);
        listPanel.add(vegs);
        
        
        
        
        
        
        convert.addActionListener(new ActionListener()
                {
                    
                    
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        if (listPanel.isVisible())
                        {
                            convert.setText("Convert");
                            listPanel.setVisible(false);
                            comboPanel.setVisible(true);
                            number.remove(0);
                        }
                        else
                        {
                            convert.setText("Start Over");
                            listPanel.setVisible(true);
                            comboPanel.setVisible(false);
                            String disply = null;
                           
                            
                            String conAmt = amount.getText();
                            String conversionAbr = currency.getSelectedItem().toString();
                             String URLstr = "http://themoneyconverter.com/rss-feed/";
                             SyndFeed feed = null;
                        

   
                             String FromCurrency = "USD";
                             StringBuilder url = new StringBuilder(URLstr).append(FromCurrency).append("/rss.xml");
                             try{
                                URL feedURL = new URL(url.toString());
                                
                                SyndFeedInput input = new SyndFeedInput();
                                feed = input.build(new XmlReader(feedURL));
                                System.out.println(feed);
                                
                                
                                
                                
                             }
                             catch(Exception e)
                             {
                                 
                             }
                        
                                ConversionObj[] conversion = new ConversionObj[100];                 
                                for (int i = 0; i < 100; i++)
                                {
                                    conversion[i] = new ConversionObj();
                                }


                                int count = 0;
                                if (feed != null) {
                                for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
                                    String title = entry.getTitle();
                                    String descr = entry.getDescription().getValue();

                                    conversion[count].setTitle(title);
                                    conversion[count].setDesc(descr);
                                    count++;
                                }
                                
                               ConversionObj search = new ConversionObj();
                               String conversionNumber = null;
                               
                               for (int i = 0; i < 100; i++)
                               {
                                   if (conversion[i] != null)
                                   {
                                       String[] parts = conversion[i].getTitle().split("/");
                                       String compare = parts[0];
                                       if (compare.equals(conversionAbr))
                                       {
                                           String[] pieces = conversion[i].getDesc().split("=");
                                           String [] words = pieces[1].split(" ");
                                           conversionNumber = words[1];
                                           
                                          float amountOfDollars = Float.parseFloat(conAmt);
                                          float multiple = Float.parseFloat(conversionNumber);
                                          float total = amountOfDollars * multiple;
                               
                                            number.addElement(total);
                                           
                                           
                                       }
                                   }
                               }
                               

                               

            

                            

                            
                        }
                        
                        
                        
                     
                    }
                        
                    }
                });
                
        
         Update.addActionListener(new ActionListener()
                {
                    
                    
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {


                        
                             String URLstr = "http://themoneyconverter.com/rss-feed/";
                             SyndFeed feed = null;
                        

   
                             String FromCurrency = "USD";
                             StringBuilder url = new StringBuilder(URLstr).append(FromCurrency).append("/rss.xml");
                             try{
                                URL feedURL = new URL(url.toString());
                                
                                SyndFeedInput input = new SyndFeedInput();
                                feed = input.build(new XmlReader(feedURL));
                                System.out.println(feed);
                                
                                
                                
                                
                             }
                             catch(Exception e)
                             {
                                 
                             }
                        
            ConversionObj[] conversion = new ConversionObj[100];                 
            for (int i = 0; i < 100; i++)
            {
                conversion[i] = new ConversionObj();
            }
                             
                             
            int count = 0;
            if (feed != null) {
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
                String title = entry.getTitle();
                String descr = entry.getDescription().getValue();
                
                conversion[count].setTitle(title);
                conversion[count].setDesc(descr);
                count++;
            }
            
            String [] JSONarry = new String[100];
                
               for(int i = 0; i < 100; i++)
               {
                   if (conversion[i].getTitle() != null)
                   {
                       JSONSerializer serial = new JSONSerializer();
                       JSONarry[i] = serial.serialize(conversion[i]);
                       
                   }
               }
               
               
               
                
                try{
                     BufferedWriter writer = new BufferedWriter(new FileWriter("saveData.txt"));
                     for (int i = 0; i < 100; i++)
                     {
                         writer.write(JSONarry[i]);
                     }
                     
                     writer.close();
                }
                catch(Exception e)
                {
                    
                }
                        
                    
                }   
                   
                   JOptionPane.showMessageDialog(comboPanel, "Conversions Updated!");
                        
            
                    }
                }
                
                    );
         
         
         
                  About.addActionListener(new ActionListener()
                {
                    
                    
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                    
                        JOptionPane.showMessageDialog(comboPanel, "Made By Jeremy Shields!");
                    
                    }
                
                
                
                });
        
                //Put the two JPanels and JButton in different areas.
        guiFrame.add(comboPanel, BorderLayout.NORTH);
        guiFrame.add(listPanel, BorderLayout.CENTER);
        guiFrame.add(convert,BorderLayout.SOUTH);
        guiFrame.add(Update, BorderLayout.WEST);
        guiFrame.add(About, BorderLayout.EAST);
        amount.setPreferredSize(new Dimension(50, 20));
        
        //make sure the JFrame is visible
        guiFrame.setVisible(true);
        
        

       
       
    }
    
           private float convert(float x, float y)
       {
           return x * y;
       }
    
}
