package com.github.danielrodj.util;

public class ComboBoxItem {

    private Integer id;
    private String name;

    public ComboBoxItem(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
