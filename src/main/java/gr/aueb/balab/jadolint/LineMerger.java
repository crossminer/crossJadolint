/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blue
 */
public class LineMerger {
    
    public String mergeLines(File dockerFile) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dockerFile)));
        String line = null;
        String newLine = "";
        boolean hasHealthcheck = false;
        boolean isContinued = false;
        while ((line = reader.readLine()) != null) {
            if (hasInstruction(line) && line.endsWith(" \\")) {
                //newLine = "";
                newLine += line;
                isContinued = true;
            } else if (line.endsWith(" \\") && isContinued) {
                newLine = newLine + " " + line;
            } else if (newLine != null && newLine.contains("HEALTHCHECK") && !hasHealthcheck) {
                newLine += line;
                hasHealthcheck = true;
            } else if (!hasInstruction(line) && !line.endsWith(" \\") && isContinued) {
                newLine = newLine + " " + line;
                newLine = newLine.replace(" \\", "");
                newLine = newLine.trim().replaceAll(" +", " ");
                newLine += "\n";
                isContinued = false;
            } else if (hasInstruction(line) && !line.endsWith(" \\")) {
                newLine += line.trim().replaceAll(" +", " ");
                newLine += "\n";
            }
        }
        reader.close();

        return newLine;
    }
    
    private boolean hasInstruction(String line){
        
        if (line.contains("ADD") || line.contains("ARG") || line.contains("CMD") || line.contains("COPY") || line.contains("ENTRYPOINT") || line.contains("ENV") || line.contains("EXPOSE") || 
                line.contains("FROM") || line.contains("HEALTHCHECK") || line.contains("LABEL") || line.contains("MAINTAINER") || line.contains("ONBUILD") || line.contains("RUN") || 
                line.contains("SHELL") || line.contains("STOPSIGNAL") || line.contains("USER") || line.contains("VOLUME") || line.contains("WORKDIR")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String[] args){
        
        try {
            LineMerger l = new LineMerger();
            
            System.out.println(l.mergeLines(new File("/Users/blue/repos/scava-deployment/metric-platform/Dockerfile")));
        } catch (IOException ex) {
            Logger.getLogger(LineMerger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
