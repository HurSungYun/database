package simpledbms;

import java.io.*;

public class Reference implements Serializable {
    String refer, refee, table;

    public Reference(String refer, String refee, String table) {
        this.refer = refer;
        this.refee = refee;
        this.table = table;
    }
}
