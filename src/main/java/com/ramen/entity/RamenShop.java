package com.ramen.entity;

public class RamenShop {
    private String name;
    private String address;
    private String description;

    public RamenShop(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getDescription() { return description; }
}
