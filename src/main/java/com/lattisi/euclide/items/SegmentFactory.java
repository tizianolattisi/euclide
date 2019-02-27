package com.lattisi.euclide.items;

public class SegmentFactory implements ItemFactory<Segment> {

    @Override
    public Segment build(String name) {
        Segment segment = new Segment(name);
        String startPointName = name.substring(0, 1);
        String endPointName = name.substring(1);
        PointFactory pointFactory = new PointFactory();
        Point startPoint = pointFactory.build(startPointName);
        Point endPoint = pointFactory.build(endPointName);
        segment.setPoints(startPoint, endPoint);
        return segment;
    }

}
