package com.lattisi.euclide.items;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Segment extends AbstractItem implements Item {

    private Point startPoint;
    private Point endPoint;

    public Segment(String name) {
        super(name);
    }

    public ItemType type() {
        return ItemType.SEGMENT;
    }

    public Collection<String> aliases() {
        return null;
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

}
