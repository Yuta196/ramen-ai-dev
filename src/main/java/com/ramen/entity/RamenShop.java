package com.ramen.entity;

public class RamenShop {
    private int id;
    private String name;
    private String address;
    private String description;

    public RamenShop(int id, String name, String address, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getDescription() { return description; }
}
