package com.MySiteTest.pojo;

/**
 * Created on 2020/6/25
 */
public class Type {
    private String type_name;

    public Type(String type_name) {
        this.type_name = type_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type_name='" + type_name + '\'' +
                '}';
    }
}
