/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Copy;
import gr.aueb.balab.jadolint.model.Dockerfile;
import gr.aueb.balab.jadolint.model.From;
import gr.aueb.balab.jadolint.model.Line;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blue
 */
public class CopyRules implements Rule {
    private Copy copy;
    
    public boolean checkDL3010(){
        
        List<String> src = copy.getSrc();
        
        for(String s : src){
            if(s.contains(".tar"))
                return false;
        }
        
        return true;
    }
    
    public boolean checkDL3021(){
        
        List<String> src = copy.getSrc();
        
        if(src.size() >= 2){
            if(!copy.getDest().endsWith("/"))
                return false;
        }
        
        return true;
    }
    
    public boolean checkDL3022(Dockerfile doc){
        
        List<String> src = copy.getSrc();
        
        for(String s : src){
            if(s.contains("--from")) {
                String alias = s.split("=")[1];
                for(Line line : doc.getLines()){
                    if(line.getInstruction().equals("FROM")){
                        From from = new From(line.getLine());
                        if(from.getAlias().equals(alias)){
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        
        return true;
    }
    
    public boolean checkDL3023(Dockerfile doc, int lineNumber){
        
        Line line = null;
        
        List<String> src = copy.getSrc();
        
        for(String s : src){
            if(s.contains("--from")) {
                String alias = s.split("=")[1];
                for(Line l : doc.getLines()){
                    if(l.getLineNumber() > lineNumber)
                        break;
                    if(l.getInstruction().equals("FROM")){
                        line = l;
                    }
                }

                if(line != null && new From(line.getLine()).getAlias() != null && new From(line.getLine()).getAlias().equals(alias))
                    return false;
            }
        }
        
        return true;
    }
    
    public CopyRules(Copy copy){
        this.copy = copy;
    }
    
}
