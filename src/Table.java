package simpledbms;

import java.util.*;
import java.io.*;

public class Table implements Serializable {
    String name;
    HashMap<String, Column> columns;
    HashSet<String> pk;
    HashMap<String, Reference> refer;
    // table names of references
    HashSet<String> references;
    HashSet<String> referenced;

    public Table(String name, List<Column> c, List<String> p, List<Reference> r) throws Errors.DBError {
        this.name = name;
        // add columns
        for (Column C : c) {
            if (columns.get(C.name) != null)
                throw new Errors.DuplicateColumnDefError();
            columns.put(C.name, C);
        }
        // set PK
        for (String s : p) {
            Column C = columns.get(s);
            if (C == null)
                throw new Errors.NonExistingColumnDefError(s);
            pk.add(s);
        }
        // add references, validation is done in SchemaManager
        for (Reference R : r) {
            if (columns.get(R.refer) == null)
                throw new Errors.NonExistingColumnDefError(R.refer);
            if (refer.get(R.refer) != null)
                throw new Errors.DuplicateReferenceColumnError();
            refer.put(R.refer, R);
            references.add(R.table);
        }
    }

    public void desc() {
        for (String name : columns.keySet()) {
        }
    }
}
