/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.model;

import gr.aueb.balab.jadolnt.util.JadolintUtils;
import java.util.List;

/**
 *
 * @author blue
 */
public class Line {
    
    private int lineNumber;
    private String line;
    private String instruction;

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLine() {
        return line;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setLine(String line) {
        this.line = line;
    }
    
    public Line(String line, int lineNumber){
        this.line = line;
        this.lineNumber = lineNumber;
        
        instruction = JadolintUtils.getInstruction(line);
    }
    
}
