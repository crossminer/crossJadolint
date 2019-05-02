/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Maintainer;

/**
 *
 * @author blue
 */
public class MaintainerRules implements Rule {
    private Maintainer maintainer;
    
    public boolean checkDL4000(){
        
        return false;
    }
    
    public MaintainerRules(Maintainer maintainer){
        this.maintainer = maintainer;
    }
}
