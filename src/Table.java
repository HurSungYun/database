package simpledbms;

import java.util.*;
import java.io.*;

public class Table implements Serializable {
    String name;
    HashMap<String, Column> columns = new HashMap<String, Column>();
    HashSet<String> pk = new HashSet<String>();
    // table names of references
    HashSet<String> references = new HashSet<String>();
    HashSet<String> referenced = new HashSet<String>();

    public Table(String name, List<Column> c, List<String> p, int pl) throws Errors.DBError {
        this.name = name;
        // add columns
        for (Column C : c) {
            if (C.type.equals(new Type(1,0)))
                throw new Errors.CharLengthError();
            if (columns.get(C.name) != null)
                throw new Errors.DuplicateColumnDefError();
            columns.put(C.name, C);
        }
        // set PK
        if (pl != p.size())
            throw new Errors.DuplicatePrimaryKeyDefError();
        for (String s : p) {
            Column C = columns.get(s);
            if (C == null)
                throw new Errors.NonExistingColumnDefError(s);
            C.notnull = true;
            C.pk = true;
            pk.add(s);
        }
    }

    public void desc() {
        System.out.println("table_name ["+name+"]");
        System.out.println("column_name\t\ttype\t\tnull\t\tkey");
        for (Column c : columns.values()) {
            System.out.println(c.toString());
        }
    }
}
