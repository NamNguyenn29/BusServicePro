package com.example.model;

public class Route {
    private String name;

    public Route(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
