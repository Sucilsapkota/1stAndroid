package com.example.sucil.sqlitedbase;

/**
 * Created by Suhel 182 on 6/18/2016.
 */
public class Students {
    private String name, address;

    public Students(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
