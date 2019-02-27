package com.lattisi.euclide.items;

public abstract class AbstractItem implements Item {

    protected String name;

    public AbstractItem(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
