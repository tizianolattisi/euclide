package com.lattisi.euclide.items;

public class StraightLineFactory implements ItemFactory<StraightLine> {

    private final PointFactory pointFactory = new PointFactory();

    @Override
    public StraightLine build(String name) {
        StraightLine straightLine = new StraightLine(name);
        String startPointName = name.substring(0, 1).toUpperCase();
        String endPointName = name.substring(1).toUpperCase();
        Point firstPoint = pointFactory.build(startPointName);
        Point secondPoint = pointFactory.build(endPointName);
        straightLine.addPoint(firstPoint);
        straightLine.addPoint(secondPoint);
        return straightLine;
    }

}
