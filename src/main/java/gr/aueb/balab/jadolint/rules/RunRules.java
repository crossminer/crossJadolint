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

    public boolean checkDL3001() {
        List<RunBlock> runBlocks = run.getRunBlocks();
        for(RunBlock rb : runBlocks) {
            switch(rb.getExecutable()) {

            case "shutdown":
            case "service":
            case "ps":
            case "free":
            case "top":
            case "kill":
            case "mount":
            case "ifconfig":
            case "nano":
            case "vim":
                return false;
            }
        }

        return true;
    }
    
    public boolean checkDL3003(){
        //String findCd = "(\\s|^)cd\\s";
        List<RunBlock> runBlocks = run.getRunBlocks();
        
        for(RunBlock rb : runBlocks){
            if(rb.getExecutable().equals("cd"))
                return false;
        }
        
        return true;
    }

    public boolean checkDL3004() {
        List<RunBlock> runBlocks = run.getRunBlocks();
        for(RunBlock rb: runBlocks) {
            if(rb.getExecutable().equals("sudo")) {
                return false;
            }
        }

        return true;
    }

    public boolean checkDL3005() {
        for(RunBlock rb: run.getRunBlocks()) {
            if (rb.getExecutable().equals("apt-get")) {
                String[] paramsArray = rb.getParams().split(" ");
                for (String param: paramsArray) {
                    if(param.equals("upgrade") || param.equals("dist-upgrade")) {
                        return false;
                    }
                }
            }
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

    public boolean checkDL3009() {
        List<RunBlock> runBlocks = run.getRunBlocks();

        for(RunBlock rb: runBlocks) {
            String exec = rb.getExecutable();
            String param = rb.getParams();
            if(exec.equals("rm") && param.equals("-rf /var/lib/apt/lists/*")) {
                return true;
            }
        }
        return false;
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

    public boolean checkDL3014() {
        for(RunBlock rb : run.getAptGetInstallBlocks()) {
            String params = rb.getParams();
            if (!params.contains("install -y")) {
                return false;
            }
        }

        return true;
    }

    public boolean checkDL3015() {
        for (RunBlock rb : run.getAptGetInstallBlocks()) {
            String[] paramsArray = rb.getParams().split(" ");
            if (!paramsArray[paramsArray.length - 2].equals("--no-install-recommends")) {
                return false;
            }
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
    
    public boolean checkDL3019(){
        List<RunBlock> runBlocks = run.getApkAddBlocks();
        
        for(RunBlock b : runBlocks){
            String params = b.getParams();

            String[] paramsArray = params.split(" ");
            
            for(String s : paramsArray){
                if(s.equals("--no-cache"))
                    return true;
            }
            
            return false;
        }
        
        return true;
    }
    
    public void runRunRules(){
        this.checkDL3001();
        this.checkDL3003();
        this.checkDL3004();
        this.checkDL3005();
        this.checkDL3008();
        this.checkDL3009();
        this.checkDL3013();
        this.checkDL3014();
        this.checkDL3015();
        this.checkDL3017();
        this.checkDL3018();
        this.checkDL3019();
    }
    
    public RunRules(Run run){
        this.run = run;
    }
}
