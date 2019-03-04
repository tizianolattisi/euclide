package com.lattisi.euclide.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Triangle extends AbstractMeasurableItem implements Measurable, Container {

    private Collection<Segment> segments = new ArrayList<>();
    private Collection<Angle> angles = new ArrayList<>();


    public Triangle(String name) {
        super(name);
    }

    @Override
    public ItemType type() {
        return ItemType.TRIANGLE;
    }

    @Override
    public Collection<? extends Item> children() {
        return Stream.concat(segments.stream(), angles.stream()).collect(Collectors.toList());
    }

    @Override
    public Boolean contains(Item item) {
        long numOfChildrenWithItemNameAsAlias = children().stream()
                .filter(child -> child.aliases().contains(item.name())).count();
        return numOfChildrenWithItemNameAsAlias==1;
    }

    public void addSegment(Segment segment) {
        segments.add(segment);
    }

    public void addAngle(Angle angle) {
        angles.add(angle);
    }

}
