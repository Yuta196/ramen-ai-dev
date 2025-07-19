package com.ramen.entity;

public class RamenShop {
    private int id;
    private String name;
    private String taste;
    private String soup;
    private String spicy;

    public RamenShop() {}
    public RamenShop(int id, String name, String taste, String soup, String spicy) {
        this.id = id;
        this.name = name;
        this.taste = taste;
        this.soup = soup;
        this.spicy = spicy;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTaste() { return taste; }
    public void setTaste(String taste) { this.taste = taste; }
    public String getSoup() { return soup; }
    public void setSoup(String soup) { this.soup = soup; }
    public String getSpicy() { return spicy; }
    public void setSpicy(String spicy) { this.spicy = spicy; }
}
