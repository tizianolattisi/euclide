package com.lattisi.euclide.items;

public class Angle extends AbstractMeasurableItem implements Measurable {

    public Angle(String name) {
        super(name);
    }

    @Override
    public ItemType type() {
        return ItemType.ANGLE;
    }

}
