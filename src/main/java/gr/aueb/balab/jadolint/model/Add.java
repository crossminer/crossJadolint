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
            if (parts[0].contains(":")) {
                user = parts[0].substring(parts[0].indexOf("=") + 1,
                                parts[0].indexOf(":"));
                group = parts[0].substring(parts[0].indexOf(":") + 1);
            } else {
                user = parts[0].substring(parts[0].indexOf("=") + 1,
                                parts[0].length());
            }
            src = parts[1];
            dest = parts[2];
        } else
            if (splitLine.contains(" ")) { //first form
                String[] parts = splitLine.split(" ");
                src = parts[0];
                dest = parts[1];
            }
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

    public void setUser(String user) {
        this.user = user;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        group = this.group;
    }

}
