/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Dockerfile;
import gr.aueb.balab.jadolint.model.From;
import gr.aueb.balab.jadolint.model.Line;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author blue
 */
public class FromRules implements Rule {
    private From from;
    
    public boolean checkDL3006() {
        if (from.getTag() == null) {
            return false;
        }

        return true;
    }

    public boolean checkDL3007() {
        if (from.getTag() != null) {
            if(from.getTag().equals("latest")) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDL3024(Dockerfile doc, int lineNumber){
        
        if(from.getAlias() != null){
            for(Line l : doc.getLines()){
                if(l.getLineNumber() != lineNumber){
                    if(l.getInstruction().equals("FROM")){
                        From currentFrom = new From(l.getLine());
                        if(currentFrom.getAlias() !=  null)
                            if(from.getAlias().equals(currentFrom.getAlias()))
                                return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    public void runAddRules(Dockerfile doc, int lineNumber){
        this.checkDL3006();
        this.checkDL3007();
        this.checkDL3024(doc, lineNumber);
    }
    
    public FromRules(From from){
        this.from = from;
    }
    
}
