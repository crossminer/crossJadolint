/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Expose;

/**
 *
 * @author blue
 */
public class ExposeRules implements Rule {
    
    public boolean checkDL3011(String line){
        Expose expose = new Expose(line);
        
        if(expose.getPort() >= 0 && expose.getPort() <= 65535)
            return true;
        else
            return false;
    }
    
}
