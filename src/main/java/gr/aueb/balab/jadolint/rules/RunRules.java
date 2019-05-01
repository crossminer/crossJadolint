/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Run;
import gr.aueb.balab.jadolint.model.RunBlock;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author blue
 */
public class RunRules implements Rule {
    
    private Run run;
    
    public boolean checkDL3003(){
        //String findCd = "(\\s|^)cd\\s";
        List<RunBlock> runBlocks = run.getRunBlocks();
        
        for(RunBlock rb : runBlocks){
            if(rb.getExecutable().equals("cd"))
                return false;
        }
        
        return true;
    }
    
    public boolean checkDL3008(){
        List<RunBlock> runBlocks = run.getAptGetInstallBlocks();
        
        for(RunBlock b : runBlocks){
            String params = b.getParams();

            String[] paramsArray = params.split(" ");

                if(!paramsArray[paramsArray.length - 1].contains("="))
                    return false;
        }
        
        return true;
    }
    
    public boolean checkDL3013(){
        List<RunBlock> runBlocks = run.getPipInstallBlocks();
        
        for(RunBlock b : runBlocks){
            String params = b.getParams();

            String[] paramsArray = params.split(" ");

                if(!paramsArray[paramsArray.length - 1].contains("=="))
                    return false;
        }
        
        return true;
    }
    
    public boolean checkDL3017(){
        List<RunBlock> runBlocks = run.getApkUpgradeBlocks();
        
        if(!runBlocks.isEmpty())
            return false;
        
        return true;
    }
    
    public boolean checkDL3018(){
        List<RunBlock> runBlocks = run.getApkAddBlocks();
        
        for(RunBlock b : runBlocks){
            String params = b.getParams();

            String[] paramsArray = params.split(" ");

                if(!paramsArray[paramsArray.length - 1].contains("="))
                    return false;
        }
        
        return true;
    }
    
    public RunRules(Run run){
        this.run = run;
    }
}
