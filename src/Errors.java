package simpledbms;


public class Errors {
    public static class DBError extends Exception {}
    public static class CreateError extends DBError {
        public String getMessage() {
            return "Create table has failed: ";
        }
    }
    public static class DuplicateColumnDefError extends CreateError{
        public String getMessage() {
            return super.getMessage() + "column definition is duplicated";
        }
    }
    public static class DuplicateReferenceColumnError extends CreateError {
        public String getMessage() {
            return super.getMessage() + "a column references more than one column";
        }
    }
    public static class DuplicatePrimaryKeyDefError extends CreateError{
        public String getMessage() {
            return super.getMessage() + "primary key definition is duplicated";
        }
    }
    public static class NoColumnForPrimaryKeyError extends CreateError {
        String name;
        public NoColumnForPrimaryKeyError(String n) {
            name = n;
        }
        public String getMessage() {
            return super.getMessage() + "table definition has no column \'"+name+"\'";
        }
    }
    public static class ReferenceTypeError extends CreateError {
        public String getMessage() {
            return super.getMessage() + "foreign key references wrong type";
        }
    }
    public static class ReferenceNonPrimaryKeyError extends CreateError{
        public String getMessage() {
            return super.getMessage() + "foreign key references non primary key column";
        }
    }
    public static class ReferenceColumnExistenceError extends CreateError{
        public String getMessage() {
            return super.getMessage() + "foreign key references non existing column";
        }
    }
    public static class ReferenceTableExistenceError extends CreateError{
        public String getMessage() {
            return super.getMessage() + "foreign key references non existing table";
        }
    }
    public static class NonExistingColumnDefError extends CreateError {
        private String name;
        public NonExistingColumnDefError(String n) {
            name = "\'"+n+"\'";
        }
        public String getMessage() {
            return super.getMessage() + name + " does not exist in column definition";
        }
    }
    public static class TableExistenceError extends CreateError {
        public String getMessage() {
            return super.getMessage() + "table with same name already exists";
        }
    }
    public static class DropError extends DBError {
        public String getMessage() {
            return "Drop table failed: ";
        }
    }
    public static class DropReferencedTableError extends DropError {
        String name;
        public DropReferencedTableError(String n) {
            name = n;
        }
        public String getMessage() {
            return super.getMessage() + name + " is referenced by other table";
        }
    }
    public static class ShowTablesNoTable extends DBError {
        public String getMessage() {
            return "There is no table";
        }
    }
    public static class NoSuchTable extends DBError {
        public String getMessage() {
            return "No such table";
        }
    }
    public static class CharLengthError extends DBError {
        public String getMessage() {
            return "Char length should be over 0";
        }
    }
}
