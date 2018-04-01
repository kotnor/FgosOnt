package model.reqtostructure;

public interface Block {
    String getName();
    double getSize();
    void parseSize(String table);
}
