package model.reqtostructure;

import java.io.Serializable;

public interface Block extends Serializable{
    String getName();
    double getSize();
    void parseSize(String table);
}
