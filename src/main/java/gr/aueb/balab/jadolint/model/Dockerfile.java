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
public class Dockerfile {
    
    private List<Line> lines = new ArrayList<>();
    private List<Violation> violations;
    
    public void addLine(Line line){
        lines.add(line);
    }
    
    public void addViolation(Line violation){
        lines.add(violation);
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Violation> getViolations() {
        return violations;
    }
    
}
