/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Cmd;
import gr.aueb.jadolint.violations.Violation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blue
 */
public class CmdRules implements Rule {
    private Cmd cmd;
    
    public boolean checkDL3025(){
        
        if(!cmd.isExecForm())
            return false;
        
        return true;
    }
    
    public List<Violation> runCmdRules(){
        List<Violation> violations = new ArrayList<>();
        if(this.checkDL3025() == false)
            violations.add(new Violation("DL3025", "Use arguments JSON notation for CMD and ENTRYPOINT arguments"));
        
        return violations;
    }
    
    public CmdRules(Cmd cmd){
        this.cmd = cmd;
    }
}
