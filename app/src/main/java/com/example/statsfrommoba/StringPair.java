package com.example.statsfrommoba;

import java.util.Comparator;

public class StringPair implements Comparable {
    public String key;
    public String value;

    public StringPair(String key, String value) {
        add(key,value);
    }

    public void add(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public static Comparator<StringPair> KeyComparator = new Comparator<StringPair>() {
        @Override
        public int compare(StringPair o1, StringPair o2) {
            String Key1 = o1.key.toUpperCase();
            String Key2 = o2.key.toUpperCase();
            return Key1.compareTo(Key2);
        }
    };

    public static Comparator<StringPair> ValueComparator = new Comparator<StringPair>() {
        @Override
        public int compare(StringPair o1, StringPair o2) {
            String Value1 = o1.value.toUpperCase();
            String Value2 = o2.value.toUpperCase();
            Integer v1 = Integer.valueOf(Value1);
            Integer v2 = Integer.valueOf(Value2);
            return v1.compareTo(v2);
        }
    };

    @Override
    public int compareTo(Object o) {
        String comparekey=((StringPair) o).key;
        return this.key.compareToIgnoreCase(comparekey);
    }
}

