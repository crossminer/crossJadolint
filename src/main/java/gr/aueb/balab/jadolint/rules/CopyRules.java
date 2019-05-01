/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Copy;
import java.util.List;

/**
 *
 * @author blue
 */
public class CopyRules implements Rule {
    private Copy copy;
    
    public boolean checkDL3010(){
        //String findCd = "(\\s|^)cd\\s";
        
        List<String> src = copy.getSrc();
        
        for(String s : src){
            if(s.endsWith(".tar") || s.endsWith(".bz2") || s.endsWith(".gz") || s.endsWith(".xz") || s.endsWith(".7z") || s.endsWith(".lzx") || s.endsWith(".s7z") || s.endsWith(".rar") || s.endsWith(".lzma") ||
                    s.endsWith(".tlz") || s.endsWith(".txz") || s.endsWith(".zip"))
                return false;
        }
        
        return true;
    }
    
    public CopyRules(Copy copy){
        this.copy = copy;
    }
    
}
