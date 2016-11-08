package simpledbms;

import java.util.*;
import java.io.*;


public class Column implements Serializable {
    String name;
    Type type;
    boolean notnull;
    String ref;

    public Column(String n, Type t, boolean n_n) {
        name = n;
        type = t;
        notnull = n_n;
    }
}
