package com.lattisi.euclide.items;

import java.util.Collection;

public class Angle extends AbstractMeasurableItem implements Measurables {

    public Angle(String name) {
        super(name);
    }

    @Override
    public ItemType type() {
        return ItemType.ANGLE;
    }

    @Override
    public Collection<String> aliases() {
        return null;
    }

}
