package simpledbms;

import com.sleepycat.je.*;
import java.util.*;

public class SchemaManager {
    HashMap<String, Table> schema;

    public SchemaManager(HashMap<String, Table> t) {
        schema = t == null ? new HashMap<String, Table>() : t;
    }

    public void createTable(Table t, List<Reference> l) throws Errors.DBError {
        // check duplicate name
        if (schema.get(t.name) != null)
            throw new Errors.TableExistenceError();
        // iter through reference
        for (Reference r : l) {
            Table rt = schema.get(r.table);
            if (rt == null)
                throw new Errors.ReferenceTableExistenceError();
            if (r.refer.size() != r.refee.size())
                throw new Errors.ReferenceTypeError();
            Iterator<String> er = r.refer.iterator();
            Iterator<String> ee = r.refee.iterator();
            while (er.hasNext()) {
                String ner = er.next();
                String nee = ee.next();
                Column cer = t.columns.get(ner);
                Column cee = rt.columns.get(nee);
                if (cer == null)
                    throw new Errors.NonExistingColumnDefError(ner);
                if (cee == null)
                    throw new Errors.ReferenceColumnExistenceError();
                if (!cee.type.equals(cer.type))
                    throw new Errors.ReferenceTypeError();
            }
            if (!(rt.pk.containsAll(r.refee)&&r.refee.containsAll(rt.pk)))
                throw new Errors.ReferenceNonPrimaryKeyError();
            er = r.refer.iterator();
            ee = r.refee.iterator();
            while (er.hasNext()) {
                String ner = er.next();
                String nee = ee.next();
                Column cer = t.columns.get(ner);
                cer.reft = r.table;
                cer.refc = nee;
            }
            t.references.add(rt.name);
            rt.referenced.add(t.name);
        }
        schema.put(t.name, t);
        System.out.println("\'"+t.name+"\' table is created");
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

    public void dropTable(String name) throws Errors.DBError {
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
        System.out.println("\'"+name+"\' table is dropped");
    }

    void printBar() {
        System.out.println("----------------------------------");
    }
}
