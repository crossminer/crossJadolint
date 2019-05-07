/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Dockerfile;
import gr.aueb.balab.jadolint.model.From;
import gr.aueb.balab.jadolint.model.Line;
import gr.aueb.jadolint.violations.Violation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    
    public List<Violation> runAddRules(Dockerfile doc, int lineNumber){
        List<Violation> violations = new ArrayList<>();
        if(this.checkDL3006() == false)
            violations.add(new Violation("DL3006" ,"Always tag the version of an image explicitly"));
        if(this.checkDL3007() == false)
            violations.add(new Violation("DL3007" ,"Using latest is prone to errors if the image will ever update. Pin the version explicitly to a release tag"));
        if(this.checkDL3024(doc, lineNumber) == false)
            violations.add(new Violation("DL3024" ,"FROM aliases (stage names) must be unique"));
        
        return violations;
    }
    
    public FromRules(From from){
        this.from = from;
    }
    
}
