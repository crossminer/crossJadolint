/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Workdir;
import gr.aueb.jadolint.violations.Violation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blue
 */
public class WorkdirRules implements Rule {
    
    private Workdir workdir;
    
    public boolean checkDL3000(){
        if(!workdir.getPath().startsWith("/")){
            if(workdir.getPath().startsWith("{"))
                return true;
            else
                return false;
        } else
            return true;
    }
    
    public List<Violation> runWorkdirRules(){
        List<Violation> violations = new ArrayList<>();
        if(this.checkDL3000() == false)
            violations.add(new Violation("DL3000", "Use absolute WORKDIR"));
        
        return violations;
    }
    
    public WorkdirRules(Workdir workdir){
        this.workdir = workdir;
    }
    
}
