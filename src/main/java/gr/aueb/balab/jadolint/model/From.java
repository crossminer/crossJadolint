/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author blue
 */
public class From {
    
    private String image;
    private String tag;
    private String digest;
    private String as;
    
    public From(String line){
        String splitLine = line.split(" ", 2)[1];
        
        Pattern pattern = Pattern.compile(" ");
        Matcher matcher = pattern.matcher(splitLine);
        
        if(matcher.find()){
            ;
        } else if(splitLine.contains(":")){
            String[] parts = splitLine.split(":");
            image = parts[0];
            tag = parts[1];
        } else if(splitLine.contains("@")){
            String[] parts = splitLine.split(":");
            image = parts[0];
            digest = parts[1];
        } else{
            image = splitLine;
        }
        
        
    }
    
}
