package com.example.rickmortymvvm;

import java.io.Serializable;

public class Location implements Serializable {
    private String name;
    private String url;

    public String getName() {
        return  name;
    }

    public String getUrl() {
        return url;
    }
}
