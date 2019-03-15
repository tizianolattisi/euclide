package com.lattisi.euclide.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StraightLine extends AbstractItem implements Container {

    private List<Point> points  = new ArrayList<>();

    public StraightLine(String name) {
        super(name);
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

    @Override
    public ItemType type() {
        return ItemType.STRAIGHT_LINE;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public void addOuterPointFromPoint(Point thePointToAdd, Point theNearestInnerPoint) {
        Point firstPoint = points.get(0);
        if (firstPoint.equals(theNearestInnerPoint)) {
            points.add(0, thePointToAdd);
        } else {
            Point lastPoint = points.get(points.size() - 1);
            if (lastPoint.equals(theNearestInnerPoint)) {
                points.add(thePointToAdd);
            }
        }
    }

}
