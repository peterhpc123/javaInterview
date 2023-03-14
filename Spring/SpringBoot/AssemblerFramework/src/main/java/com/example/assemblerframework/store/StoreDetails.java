package com.example.assemblerframework.store;

import lombok.Data;

import java.util.Map;

public class StoreDetails {
    private String name;

    public String getName() {
        return name;
    }

    public StoreDetails setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StoreDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public StoreDetails setClassName(String className) {
        this.className = className;
        return this;
    }

    private String description;

    private String className;
    private Map<String, Object> store;

    public Map<String, Object> getStore() {
        return store;
    }

    public void setStore(Map<String, Object> store) {
        this.store = store;
    }
}
