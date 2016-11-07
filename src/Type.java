package simpledbms;

public class Type {
    public static final int INT = 0;
    public static final int CHAR = 1;
    public static final int DATE = 2;

    int type, size;
    Type(int type, int size) {
        this.type = type;
        this.size = size;
    }

    boolean equals(Type t) {
        return type==t.type&&(type==1?size==t.size:true);
    }
}
