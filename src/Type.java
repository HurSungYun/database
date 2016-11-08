package simpledbms;

import java.io.Serializable;

public class Type implements Serializable {
    public static final int INT = 0;
    public static final int CHAR = 1;
    public static final int DATE = 2;

    int type, size;
    Type(int type, int size) throws Errors.CharLengthError {
        this.type = type;
        this.size = size;
        if (type == 1 && size == 0)
            throw new Errors.CharLengthError();
    }

    boolean equals(Type t) {
        return type==t.type&&(type==1?size==t.size:true);
    }
}
