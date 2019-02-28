package com.lattisi.euclide.items;

import java.util.*;

public class Segment extends AbstractMeasurableItem implements Measurable {

    private Point startPoint;
    private Point endPoint;

    public Segment(String name) {
        super(name);
    }

    public ItemType type() {
        return ItemType.SEGMENT;
    }

    public void setPoints(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Set<Point> points() {
        HashSet<Point> points = new HashSet<>();
        points.add(startPoint);
        points.add(endPoint);
        return points;
    }

    public Collection<String> aliases() {
        return Arrays.asList(startPoint.name()+endPoint.name(), endPoint.name()+startPoint.name());
    }

    public Optional<Point> intersection(Segment segment) {
        Set<Point> pointOfThisSegment = points();
        pointOfThisSegment.retainAll(segment.points());
        if (pointOfThisSegment.size()==1) {
            return Optional.of(pointOfThisSegment.iterator().next());
        }
        return Optional.empty();
    }

    public Point otherPoint(Point thePointWeDontWont) {
        Optional<Point> thePointWeWant = points().stream().filter(point -> !point.equals(thePointWeDontWont)).findFirst();
        assert thePointWeWant.isPresent();
        return thePointWeWant.get();
    }

}
