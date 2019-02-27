package com.lattisi.euclide.items;

public class PointFactory implements ItemFactory<Point> {

    @Override
    public Point build(String name) {
        Point point = new Point(name);
        return point;
    }

}
