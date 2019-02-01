/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blue
 */
public class Run implements Instruction{
    
    List<RunBlock> runBlocks = new ArrayList<>();
    
    public Run(String line){
        String lineWithoutInstruction = line.split(" ", 2)[1];
        
        String regex = "\\s*&&\\s*";
        
        String[] splitLine = lineWithoutInstruction.split(regex);
        
        for(String s : splitLine)
            runBlocks.add(new RunBlock(s));
    }

    public List<RunBlock> getRunBlocks() {
        return runBlocks;
    }

    public void setRunBlocks(List<RunBlock> runs) {
        this.runBlocks = runs;
    }
    
}
