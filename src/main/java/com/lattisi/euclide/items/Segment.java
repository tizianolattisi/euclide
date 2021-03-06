package com.lattisi.euclide.items;

import java.util.*;

public class Segment extends AbstractMeasurableItem implements Measurable, Container {

    private Collection<Point> points  = new ArrayList<>();

    public Segment(String name) {
        super(name);
    }

    public ItemType type() {
        return ItemType.SEGMENT;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public Optional<Point> intersection(Segment segment) {
        HashSet<Point> pointOfThisSegment = new HashSet<>();
        children().stream()
                .map(point -> (Point) point)
                .forEach(point -> pointOfThisSegment.add(point));
        pointOfThisSegment.retainAll(segment.children());
        if (pointOfThisSegment.size()==1) {
            return Optional.of(pointOfThisSegment.iterator().next());
        }
        return Optional.empty();
    }

    public Point otherPoint(Point thePointWeDontWont) {
        Optional<Point> thePointWeWant = children().stream()
                .map(point -> (Point) point)
                .filter(point -> !point.equals(thePointWeDontWont)).findFirst();
        assert thePointWeWant.isPresent();
        return thePointWeWant.get();
    }

    @Override
    public Collection<? extends Item> children() {
        return points;
    }

    @Override
    public Boolean contains(Item item) {
        long numOfChildrenWithItemNameAsAlias = children().stream()
                .filter(child -> child.aliases().contains(item.name())).count();
        return numOfChildrenWithItemNameAsAlias==1;
    }

}
