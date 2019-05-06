/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Cmd;

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
    
    public void runCmdRules(){
        this.checkDL3025();
    }
    
    public CmdRules(Cmd cmd){
        this.cmd = cmd;
    }
}
