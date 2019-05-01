/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Add;
import java.util.List;

/**
 *
 * @author blue
 */
public class AddRules implements Rule {
    private Add add;
    
    public boolean checkDL3020(){
        //String findCd = "(\\s|^)cd\\s";
        
        List<String> src = add.getSrc();
        
        for(String s : src){
            if(!s.contains(".tar"))
                return false;
        }
        
        return true;
    }
    
    public AddRules(Add add){
        this.add = add;
    }
    
}
