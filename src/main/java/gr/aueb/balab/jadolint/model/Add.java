package gr.aueb.balab.jadolint.model;

public class Add {
    private String src;
    private String dest;
    private String user;
    private String group;

    public Add(String line) {
        String splitLine = line.split(" ", 2)[1]; //ADD command discarded

        if (splitLine.startsWith("--chown")) {
            String[] parts = splitLine.split(" ");
            setUser(parts[0]);
            setGroup(parts[0]);
            src = parts[1];
            dest = parts[2];
        } else
            if (splitLine.contains(" ")) { //first form
                String[] parts = splitLine.split(" ");
                src = parts[0];
                dest = parts[1];
            }
    }

    private void setUser(String s) {
        user = s.substring(s.indexOf("=") + 1, s.indexOf(":"));
    }

    private void setGroup(String s) {
        group = s.substring(s.indexOf(":"));
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getUser() {
        return user;
    }

    public String getGroup() {
        return group;
    }

}
