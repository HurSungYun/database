package simpledbms;

import com.sleepycat.je.*;
import java.util.*;

public class SchemaManager {
    Database db;
    HashMap<String, Table> schema;

    public SchemaManager(Environment e, DatabaseConfig dc) {
        db = e.openDatabase(null, "schema", dc);
    }

    public void createTable(Table t) throws Errors.DBError {
        // check duplicate name
        if (schema.get(t.name) != null)
            throw new Errors.TableExistenceError();
        // iter through reference
        for (Reference r : t.refer.values()) {
            Table rt = schema.get(r.table);
            if (rt == null)
                throw new Errors.ReferenceTableExistenceError();
            Column c = rt.columns.get(r.refee);
            if (c == null)
                throw new Errors.ReferenceColumnExistenceError();
            if (!rt.pk.contains(c.name))
                throw new Errors.ReferenceNonPrimaryKeyError();
            if (!c.type.equals(t.columns.get(r.refer).type))
                throw new Errors.ReferenceTypeError();
            rt.referenced.add(t.name);
        }
        schema.put(t.name, t);
        save();
    }

    public void desc(String name) throws Errors.DBError {
        Table t = schema.get(name);
        if (t == null) 
            throw new Errors.NoSuchTable();
        printBar();
        t.desc();
        printBar();
    }

    public void showTables() throws Errors.DBError {
        Set<String> keys = schema.keySet();
        if (keys.isEmpty())
            throw new Errors.ShowTablesNoTable();
        printBar();
        for (String s : keys)
            System.out.println(s);
        printBar();
    }

    public void dropTable(String name) throws Errors.DBError{
        Table t = schema.get(name);
        if (t == null)
            throw new Errors.NoSuchTable();
        if (!t.referenced.isEmpty())
            throw new Errors.DropReferencedTableError(name);
        for (String r : t.references) {
            Table rt = schema.get(r);
            rt.referenced.remove(name);
        }
        schema.remove(name);
        save();
        System.out.println("\'"+name+"\' table is dropped");
    }

    void printBar() {
        System.out.println("----------------------------------");
    }
 
    public void close() {
    }

    private void save() {
    }
}
