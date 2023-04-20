package com.rudametkin.hotelsystem.EntityObjects;

public class Role {
    private int id = -1;
    private String name;

    public Role() {}
    public Role(String name) {
        this.name = name;
    }
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getRoleName() { return name; }
    public void setRoleName(String newName) { name = newName; }
}