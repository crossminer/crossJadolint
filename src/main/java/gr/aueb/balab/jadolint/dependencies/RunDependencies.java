package gr.aueb.balab.jadolint.dependencies;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.balab.jadolint.model.Run;
import gr.aueb.balab.jadolint.model.RunBlock;

public class RunDependencies {

    private List<String> packageName = new ArrayList<>();
    private List<String> packageVersion = new ArrayList<>();
    private List<RunBlock> runBlocksInstall = new ArrayList<>();

    public RunDependencies(String line) {
        runBlocksInstall = new Run(line).getAptGetInstallBlocks();

        for (RunBlock runblock : runBlocksInstall) {
            String[] splitLine = runblock.getParams().split(" ");
            String packageInfo = splitLine[splitLine.length - 1];

            if (packageInfo.contains("=")) {
                String name = packageInfo.split("=", 2)[0];
                packageName.add(name);

                String version = packageInfo.split("=", 2)[1];
                packageVersion.add(version);
            }
        }
    }
}
