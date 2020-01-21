package gr.aueb.balab.jadolint.dependencies;

import gr.aueb.balab.jadolint.model.From;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.balab.jadolint.model.Run;
import gr.aueb.balab.jadolint.model.RunBlock;
import gr.aueb.balab.jadolint.util.JadolintUtils;

public class DependencyDetector {

    private List<Dependency> dependencies = new ArrayList<Dependency>();

    public void detectDependency(String line) {
        String instruction = JadolintUtils.getInstruction(line);

        switch(instruction) {
        case "FROM":
            checkForFromDependency(line);
            break;
        case "RUN":
            checkForRunDependency(line);
            break;
        }
    }

    private void checkForFromDependency(String line) {
        From from = new From(line);
        
        String packageName = from.getImage();
        
        if(from.getTag() != null)
            dependencies.add(new FromDependency(packageName, from.getTag()));
        else
            dependencies.add(new FromDependency(packageName, null));
    }

    private void checkForRunDependency(String line) {
        List<RunBlock> runBlocksInstall = new Run(line).getAptGetInstallBlocks();

        for (RunBlock runblock : runBlocksInstall) {
            String[] splitLine = runblock.getParams().split(" ");
            //String packageInfo = splitLine[splitLine.length - 1];
            
            for(String s : splitLine) {
                
                if(s.contains("$")){
                    
                }
                
            	if(!s.equals("install") && !s.startsWith("-")) {
		            if (s.contains("=")) {
		                String name = s.split("=", 2)[0];
		                String version = s.split("=", 2)[1];
		                dependencies.add(new RunDependency(name, version));
		            } else{
		                dependencies.add(new RunDependency(s, null));
		            }
            	}
            }
        }
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }
}
