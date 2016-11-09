package simpledbms;

import java.util.*;

public class Reference {
    String table;
    List<String> refer;
    List<String> refee;

    public Reference(List<String> refer, List<String> refee, String table) {
        this.refer = refer;
        this.refee = refee;
        this.table = table;
    }
}
