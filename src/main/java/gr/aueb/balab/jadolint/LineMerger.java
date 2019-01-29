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

/**
 *
 * @author blue
 */
public class LineMerger {
    
    public String mergeLines(File dockerFile) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dockerFile)));
        String line = null;
        String newLine = null;
        boolean hasHealthcheck = false;
        boolean isContinued = false;
        while ((line = reader.readLine()) != null) {
            if (hasInstruction(line) && line.endsWith(" \\")) {
                newLine = "";
                newLine += line;
                isContinued = true;
            } else if (line.endsWith(" \\") && isContinued) {
                newLine += line;
            } else if (newLine != null && newLine.contains("HEALTHCHECK") && !hasHealthcheck) {
                newLine += line;
                hasHealthcheck = true;
            } else if (!hasInstruction(line) && !line.endsWith(" \\") && isContinued) {
                newLine += line;
                newLine = newLine.replace(" \\", "");
                newLine = newLine.trim().replaceAll(" +", " ");
                isContinued = false;
            } else if (hasInstruction(line) && !line.endsWith(" \\")) {
                newLine += line.trim().replaceAll(" +", " ");
            }
        }
        reader.close();

        return newLine;
    }
    
}
