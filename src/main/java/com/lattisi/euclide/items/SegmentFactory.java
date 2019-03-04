package com.lattisi.euclide.items;

public class SegmentFactory implements ItemFactory<Segment> {

    private final PointFactory pointFactory = new PointFactory();

    @Override
    public Segment build(String name) {
        Segment segment = new Segment(name);
        String startPointName = name.substring(0, 1);
        String endPointName = name.substring(1);
        Point startPoint = pointFactory.build(startPointName);
        Point endPoint = pointFactory.build(endPointName);
        segment.addPoint(startPoint);
        segment.addPoint(endPoint);
        return segment;
    }

}
