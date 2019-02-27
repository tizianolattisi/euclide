package com.lattisi.euclide.items;

import java.util.Arrays;
import java.util.Collection;

public class Point extends AbstractItem implements Item {

    public Point(String name) {
        super(name);
    }

    public ItemType type() {
        return ItemType.POINT;
    }

    public Collection<String> aliases() {
        return Arrays.asList(name);
    }

}
