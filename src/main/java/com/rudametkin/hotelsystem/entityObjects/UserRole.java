package com.rudametkin.hotelsystem.entityObjects;

public class UserRole {
    private int userId;
    private int roleId;

    public UserRole(int userId, int roleId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public int getUserId() { return userId; }
    public int getRoleId() { return roleId; }
    public void setRoleId(int newRoleId) { roleId = newRoleId; }
}
