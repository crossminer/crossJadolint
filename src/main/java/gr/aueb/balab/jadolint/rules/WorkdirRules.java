/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

/**
 *
 * @author blue
 */
public class WorkdirRules {
    
    private boolean checkDL3000(String line){
        if(!line.startsWith("/")){
            if(line.startsWith("{"))
                return true;
            else
                return false;
        } else
            return true;
    }
    
}
