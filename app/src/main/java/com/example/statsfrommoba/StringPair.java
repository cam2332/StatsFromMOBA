package com.example.statsfrommoba;

public class StringPair {
    public String key;
    public String value;

    public StringPair(String key, String value) {
        add(key,value);
    }

    public void add(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

