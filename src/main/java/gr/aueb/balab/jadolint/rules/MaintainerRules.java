/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Maintainer;
import gr.aueb.jadolint.violations.Violation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blue
 */
public class MaintainerRules implements Rule {
    private Maintainer maintainer;
    
    public boolean checkDL4000(){
        
        return false;
    }
    
    public List<Violation> runMaintainerRules(){
        List<Violation> violations = new ArrayList<>();
        if(this.checkDL4000() == false)
            violations.add(new Violation("DL4000", "MAINTAINER is deprecated"));
        
        return violations;
    }
    
    public MaintainerRules(Maintainer maintainer){
        this.maintainer = maintainer;
    }
}
