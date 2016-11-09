package simpledbms;

import java.util.*;
import java.io.*;


public class Column implements Serializable {
    String name;
    Type type;
    boolean notnull;
    boolean pk;
    String reft = null;
    String refc = null;

    public Column(String n, Type t, boolean n_n) {
        name = n;
        type = t;
        notnull = n_n;
    }

    public String toString() {
        return name+"\t\t"+type.toString()+"\t\t"+(notnull?"N":"Y")+"\t\t"+(pk&&refc!=null?"PRI/FOR":(pk?"PRI":(refc!=null?"FOR":"")));
    }
}
