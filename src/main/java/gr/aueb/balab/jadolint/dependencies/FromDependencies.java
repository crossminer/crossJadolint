package gr.aueb.balab.jadolint.dependencies;

public class FromDependencies {
    private String packageName;
    private String packageVersion;

    public FromDependencies(String line) {
        String lineWithoutInstruction = line.split(" ", 2)[1];

        if (lineWithoutInstruction.contains(":")) {
            packageName = lineWithoutInstruction.split(":", 2)[0];
            packageVersion = lineWithoutInstruction.split(":", 2)[1];
        } else {
            packageName = lineWithoutInstruction;
        }
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageVersion() {
        return packageVersion;
    }

    public void setPackageVersion(String packageVersion) {
        this.packageVersion = packageVersion;
    }

}
