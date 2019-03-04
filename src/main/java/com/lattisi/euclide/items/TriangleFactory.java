package com.lattisi.euclide.items;

public class TriangleFactory implements ItemFactory<Triangle> {

    private final SegmentFactory segmentFactory = new SegmentFactory();
    private final AngleFactory angleFactory = new AngleFactory();

    @Override
    public Triangle build(String name) {
        Triangle triangle = new Triangle(name);
        String firstPointName = name.substring(0, 1);
        String secondPointName = name.substring(1, 2);
        String thirdPointName = name.substring(2, 3);
        String firstSegmentName = firstPointName.toUpperCase() + secondPointName.toUpperCase();
        triangle.addSegment(segmentFactory.build(firstSegmentName));
        String secondSegmentName = secondPointName.toUpperCase() + thirdPointName.toUpperCase();
        triangle.addSegment(segmentFactory.build(secondSegmentName));
        String thirdSegmentName = thirdPointName.toUpperCase() + firstPointName.toUpperCase();
        triangle.addSegment(segmentFactory.build(thirdSegmentName));
        String firstAngleName = firstPointName.toLowerCase() + secondPointName.toLowerCase() + thirdPointName.toLowerCase();
        triangle.addAngle(angleFactory.build(firstAngleName));
        String secondAngleName = secondPointName.toLowerCase() + thirdPointName.toLowerCase() + firstPointName.toLowerCase();
        triangle.addAngle(angleFactory.build(secondAngleName));
        String thirdAngleName = thirdPointName.toLowerCase() + firstPointName.toLowerCase() + secondPointName.toLowerCase();
        triangle.addAngle(angleFactory.build(thirdAngleName));
        return triangle;
    }

}
