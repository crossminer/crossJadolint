/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Workdir;

/**
 *
 * @author blue
 */
public class WorkdirRules implements Rule {
    
    public boolean checkDL3000(String line){
        Workdir workdir = new Workdir(line);
        
        if(!workdir.getPath().startsWith("/")){
            if(line.startsWith("{"))
                return true;
            else
                return false;
        } else
            return true;
    }
    
}
