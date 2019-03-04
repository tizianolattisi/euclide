package com.lattisi.euclide.items;


public class Point extends AbstractItem implements Item {

    public Point(String name) {
        super(name);
    }

    public ItemType type() {
        return ItemType.POINT;
    }

}
