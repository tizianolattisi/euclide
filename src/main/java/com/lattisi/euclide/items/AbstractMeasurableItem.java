package com.lattisi.euclide.items;

import java.util.Optional;

public abstract class AbstractMeasurableItem extends AbstractItem implements Measurables {

    private Optional<String> measure = Optional.empty();

    public AbstractMeasurableItem(String name) {
        super(name);
    }

    @Override
    public Optional<String> measure() {
        return measure;
    }

    @Override
    public void assignMeasure(String measure) {
        this.measure = Optional.of(measure);
    }

    @Override
    public Boolean isCongruentTo(Measurables item) {
        return null;
    }
}
