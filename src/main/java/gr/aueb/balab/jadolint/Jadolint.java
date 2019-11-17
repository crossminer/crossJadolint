/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.aueb.balab.jadolint;

import gr.aueb.balab.jadolint.dependencies.Dependency;
import gr.aueb.balab.jadolint.dependencies.DependencyDetector;
import gr.aueb.balab.jadolint.model.Add;
import gr.aueb.balab.jadolint.model.Cmd;
import gr.aueb.balab.jadolint.model.Copy;
import gr.aueb.balab.jadolint.model.Dockerfile;
import gr.aueb.balab.jadolint.model.Expose;
import gr.aueb.balab.jadolint.model.From;
import gr.aueb.balab.jadolint.model.Line;
import gr.aueb.balab.jadolint.model.Maintainer;
import gr.aueb.balab.jadolint.model.Run;
import gr.aueb.balab.jadolint.model.Workdir;
import gr.aueb.balab.jadolint.rules.AddRules;
import gr.aueb.balab.jadolint.rules.CmdRules;
import gr.aueb.balab.jadolint.rules.CopyRules;
import gr.aueb.balab.jadolint.rules.ExposeRules;
import gr.aueb.balab.jadolint.rules.FromRules;
import gr.aueb.balab.jadolint.rules.MaintainerRules;
import gr.aueb.balab.jadolint.rules.RunRules;
import gr.aueb.balab.jadolint.rules.WorkdirRules;
import gr.aueb.balab.jadolint.violations.Violation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blue
 */
public class Jadolint {
    
    private Dockerfile doc;
    
    public static void main(String[] args){
        try {
            if(args.length < 1){
                System.out.println("Please provide a Dockerfile");
                return;
            }
            
            String path = args[0];
            
            LineMerger l = new LineMerger();
            Dockerfile doc = new Dockerfile();
            
            doc.setPath(path);
            
            l.mergeLines(doc, new File(path));
            
            /*List<Violation> violations = new ArrayList<>();
            
            for(Line line : doc.getLines()){
                //System.out.println(line.getLine() + " " + line.getLineNumber() + " " + line.getInstruction());
                if(line.getInstruction().equals("ADD")){
                    AddRules addRules = new AddRules(new Add(line.getLine()));
                    violations = addRules.runAddRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("CMD")){
                    CmdRules cmdRules = new CmdRules(new Cmd(line.getLine()));
                    violations = cmdRules.runCmdRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("COPY")){
                    CopyRules copyRules = new CopyRules(new Copy(line.getLine()));
                    violations = copyRules.runCopyRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("EXPOSE")){
                    ExposeRules exposeRules = new ExposeRules(new Expose(line.getLine()));
                    violations = exposeRules.runExposeRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("FROM")){
                    FromRules fromRules = new FromRules(new From(line.getLine()));
                    violations = fromRules.runFromRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("MAINTAINER")){
                    MaintainerRules maintainerRules = new MaintainerRules(new Maintainer(line.getLine()));
                    violations = maintainerRules.runMaintainerRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("RUN")){
                    RunRules runRules = new RunRules(new Run(line.getLine()));
                    violations = runRules.runRunRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("WORKDIR")){
                    WorkdirRules workdirRules = new WorkdirRules(new Workdir(line.getLine()));
                    violations = workdirRules.runWorkdirRules(doc, line.getLineNumber());
                }
                if(!violations.isEmpty())
                    doc.addViolations(violations);
            }*/
            
            getDependencies2(path, doc);
            
            //for(Violation v : doc.getViolations())
            //    System.out.println(v.getFileName() + " " + v.getLineNumber() + " " + v.getCode() + " " + v.getMessage());
            
            for(Dependency d : doc.getDependencies()){
                System.out.print(d.getPackageName().trim());
                if(d.getPackageVersion() != null)
                    System.out.println(" " + d.getPackageVersion());
                else
                    System.out.println();
            }
        } catch (IOException ex) {
            Logger.getLogger(Jadolint.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void run(String path){
        try {
            
            LineMerger l = new LineMerger();
            doc = new Dockerfile();
            
            doc.setPath(path);
            
            l.mergeLines(doc, new File(path));
            
            List<Violation> violations = new ArrayList<>();
            
            for(Line line : doc.getLines()){
                //System.out.println(line.getLine() + " " + line.getLineNumber() + " " + line.getInstruction());
                if(line.getInstruction().equals("ADD")){
                    AddRules addRules = new AddRules(new Add(line.getLine()));
                    violations = addRules.runAddRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("CMD")){
                    CmdRules cmdRules = new CmdRules(new Cmd(line.getLine()));
                    violations = cmdRules.runCmdRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("COPY")){
                    CopyRules copyRules = new CopyRules(new Copy(line.getLine()));
                    violations = copyRules.runCopyRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("EXPOSE")){
                    ExposeRules exposeRules = new ExposeRules(new Expose(line.getLine()));
                    violations = exposeRules.runExposeRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("FROM")){
                    FromRules fromRules = new FromRules(new From(line.getLine()));
                    violations = fromRules.runFromRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("MAINTAINER")){
                    MaintainerRules maintainerRules = new MaintainerRules(new Maintainer(line.getLine()));
                    violations = maintainerRules.runMaintainerRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("RUN")){
                    RunRules runRules = new RunRules(new Run(line.getLine()));
                    violations = runRules.runRunRules(doc, line.getLineNumber());
                }
                if(line.getInstruction().equals("WORKDIR")){
                    WorkdirRules workdirRules = new WorkdirRules(new Workdir(line.getLine()));
                    violations = workdirRules.runWorkdirRules(doc, line.getLineNumber());
                }
                if(!violations.isEmpty())
                    doc.addViolations(violations);
            }
            
            /*for(Violation v : doc.getViolations())
                System.out.println(v.getFileName() + " " + v.getLineNumber() + " " + v.getCode() + " " + v.getMessage());*/
            
        } catch (IOException ex) {
            Logger.getLogger(Jadolint.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getDependencies(String path) {
        try {
            LineMerger l = new LineMerger();
            doc = new Dockerfile();
            
            doc.setPath(path);
            
            l.mergeLines(doc, new File(path));
            
            DependencyDetector detector = new DependencyDetector();
            
            for(Line line : doc.getLines()){
                detector.detectDependency(line.getLine());
            }
            
            doc.addDependencies(detector.getDependencies());
        } catch (IOException ex) {
            Logger.getLogger(Jadolint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void getDependencies2(String path, Dockerfile doc) {

        DependencyDetector detector = new DependencyDetector();

        for(Line line : doc.getLines()){
            detector.detectDependency(line.getLine());
        }

        doc.addDependencies(detector.getDependencies());
        
    }

    public Dockerfile getDoc() {
            return doc;
    }

    public void setDoc(Dockerfile doc) {
            this.doc = doc;
    }
    
}
