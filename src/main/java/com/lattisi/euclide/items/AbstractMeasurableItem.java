package com.lattisi.euclide.items;

import java.util.Optional;

public abstract class AbstractMeasurableItem extends AbstractItem implements Measurable {

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
    public Boolean isCongruentTo(Measurable otherMeasurableItem) throws ItemWithoutMeasureException{
        if (anItemWithoutMeasure(otherMeasurableItem)) {
            throw new ItemWithoutMeasureException("Unable to compare items without measure.");
        }
        return this.measure.get().equals(otherMeasurableItem.measure().get());
    }

    private boolean anItemWithoutMeasure(Measurable otherMeasurableItem) {
        return !this.measure.isPresent() || !otherMeasurableItem.measure().isPresent();
    }
}
