/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Copy;
import java.util.List;

/**
 *
 * @author blue
 */
public class CopyRules implements Rule {
    private Copy copy;
    
    public boolean checkDL3010(){
        //String findCd = "(\\s|^)cd\\s";
        
        List<String> src = copy.getSrc();
        
        for(String s : src){
            if(s.contains(".tar"))
                return false;
        }
        
        return true;
    }
    
    public boolean checkDL3021(){
        //String findCd = "(\\s|^)cd\\s";
        
        List<String> src = copy.getSrc();
        
        if(src.size() >= 2){
            if(!copy.getDest().endsWith("/"))
                return false;
        }
        
        return true;
    }
    
    public CopyRules(Copy copy){
        this.copy = copy;
    }
    
}
