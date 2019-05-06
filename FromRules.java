package gr.aueb.balab.jadolint.rules;

import gr.aueb.balab.jadolint.model.Dockerfile;
import gr.aueb.balab.jadolint.model.From;
import gr.aueb.balab.jadolint.model.Line;

public class FromRules {
    From from;

    public FromRules(From from) {
        this.from = from;
    }

    public boolean checkDL3006() {
        if (from.getTag() == null) {
            return false;
        }

        return true;
    }

    public boolean checkDL3007() {
        if (from.getTag() != null) {
            if(from.getTag().equals("latest")) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDL3024(Dockerfile doc, int lineNumber){

        if(from.getAlias() != null){
            for(Line l : doc.getLines()){
                if(l.getLineNumber() != lineNumber){
                    if(l.getInstruction().equals("FROM")){
                        From currentFrom = new From(l.getLine());
                        if(currentFrom.getAlias() !=  null) {
                            if(from.getAlias().equals(currentFrom.getAlias())) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

}
