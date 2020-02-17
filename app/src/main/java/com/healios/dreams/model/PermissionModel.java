package com.healios.dreams.model;

public class PermissionModel {

    private String name;
    private Boolean isEnabled;


    public PermissionModel(String name, Boolean isEnabled) {
        this.name = name;
        this.isEnabled = isEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
