package com.nectarmicrosystems.libgdx.twoliesonefact.main;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class Statement {
    private String statement;
    private int index;
    private boolean value;

    public Statement(String statement, boolean value, int index){
        this.statement = statement;
        this.value = value;
        this.index = index;

    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public boolean isTrue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }
}
