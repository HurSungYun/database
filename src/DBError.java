package simpledbms;


public class DBError {
    public static class CreateError extends Exception {
        public String getMessage() {
            return "Create table has failed: ";
        }
    }
    public static class DuplicateColumnDefError extends CreateError{
        public String getMessage() {
            return super.getMessage() + "column definition is duplicated";
        }
    }
    public static class DuplicatePrimaryKeyDefError extends CreateError{
        public String getMessage() {
            return super.getMessage() + "primary key definition is duplicated";
        }
    }
    public static class ReferenceTypeError extends CreateError{
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
}
