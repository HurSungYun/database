package simpledbms;
import com.sleepycat.je.*;
import java.util.*;

public class SchemaManager {
    Database db;
    public SchemaManager(Environment e, DatabaseConfig dc) {
        db = e.openDatabase(null, "schema", dc);
    }

    public void createTable(String name) throws Exception {
    }

    public void desc(String name) {
    }

    public void showTables() {
    }

    public void dropTable(String name) {
    }
}
