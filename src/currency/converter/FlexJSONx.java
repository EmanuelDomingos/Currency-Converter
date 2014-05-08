/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package currency.converter;
import flexjson.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Jeremy
 */
public class FlexJSONx {
        private static String cwd;
    private static final String path = "config.json";
    private static String configFile; 
    
    private class CountryCodes {
        String Country;
        String Units;
    }
    
    private static void readFile(String path) {
        
        byte[] rawFile;  // read the entire raw configuration file into this var
        
        try {
            cwd = new File( "." ).getCanonicalPath();  // grab the current working directory
            rawFile = Files.readAllBytes(Paths.get(path));
            configFile = new String(rawFile);
            JSONDeserializer<List<CountryCodes>> deserializer = new JSONDeserializer<List<CountryCodes>>();
            List stdMap = deserializer.deserialize(configFile);
            System.out.println(stdMap);
        } catch(Exception e) {
            System.out.println(e.toString());
            System.out.println("Something went wrong! Missing file: "+cwd+"/"+path+"?");
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        readFile(path);
    }
}
