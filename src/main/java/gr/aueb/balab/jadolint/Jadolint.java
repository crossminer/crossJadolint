/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint;

import gr.aueb.balab.jadolint.model.Dockerfile;
import gr.aueb.balab.jadolint.model.Line;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blue
 */
public class Jadolint {
    
    public static void main(String[] args){
        try {
            if(args.length < 1){
                System.out.println("Please provide a Dockerfile");
                return;
            }
            
            String path = args[0];
            
            LineMerger l = new LineMerger();
            Dockerfile doc = new Dockerfile();
            
            l.mergeLines(doc, new File("/Users/blue/Desktop/yeah/Dockerfile"));
            
            for(Line line : doc.getLines()){
                //System.out.println(line.getLine() + " " + line.getLineNumber() + " " + line.getInstruction());
                if(line.getInstruction().equals("ADD")){
                    Add a = new Add(line.getLine());
                    //RunRules r = new RunRules(new Run(line.getLine()));
                    //System.out.println(r.checkDL3003() + " " + line.getLineNumber());
                    System.out.println(a.getUser() + " " + a.getGroup() + " " + a.getSrc() + " " + a.getDest());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Jadolint.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
