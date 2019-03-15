package com.lattisi.euclide;

import com.lattisi.euclide.items.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Problem {

    private final PointFactory pointFactory = new PointFactory();
    private final SegmentFactory segmentFactory = new SegmentFactory();
    private final AngleFactory angleFactory = new AngleFactory();
    private final TriangleFactory triangleFactory = new TriangleFactory();
    private final StraightLineFactory straightLineFactory = new StraightLineFactory();

    private Collection<Item> items = new HashSet<>();

    public Point addPoint(Point point) {
        items.add(point);
        return point;
    }

    public Point addPoint(String name) {
        return addPoint(pointFactory.build(name));
    }

    public Segment addSegment(Segment segment) {
        items.add(segment);
        return segment;
    }

    public Segment addSegment(String name) {
        return addSegment(segmentFactory.build(name));
    }

    public Segment addSegment(String name, String measure) {
        Segment segment = segmentFactory.build(name);
        segment.assignMeasure(measure);
        items.add(segment);
        return segment;
    }

    public Angle addAngle(Angle angle) {
        items.add(angle);
        return angle;
    }

    public Angle addAngle(Segment firstSegment, Segment secondSegment) {
        return addAngle(angleFactory.build(firstSegment, secondSegment));
    }

    public Triangle addTriangle(Triangle triangle) {
        items.add(triangle);
        return triangle;
    }

    public Triangle addTriangle(String name) {
        return addTriangle(triangleFactory.build(name));
    }

    public void refresh() {
        discoverSegmentsFromTriangle();
        discoverPointsFromSegment();
        discoverAnglesFromSegments();
        discoverTrianglesFromSegments();
    }

    private void discoverSegmentsFromTriangle() {
        triangles().stream()
                .forEach(triangle -> {
                    triangle.children().stream()
                            .filter(item -> item.type().equals(ItemType.SEGMENT))
                            .forEach(segment -> {
                                if (!findItemByName(segment.name()).isPresent()) {
                                    addSegment((Segment) segment);
                                }
                            });
                });
    }

    public List<Triangle> triangles() {
        return items.stream().filter(item -> item.type().equals(ItemType.TRIANGLE)).map(item -> (Triangle) item).collect(Collectors.toList());
    }


    private void discoverPointsFromSegment() {
        segments().stream()
                .forEach(segment ->
                    segment.children().stream()
                            .filter(item -> item.type().equals(ItemType.POINT))
                            .forEach(point -> {
                                if (!findItemByName(point.name()).isPresent()) {
                                    addPoint((Point) point);
                                }
                            })
                );
    }

    private void discoverAnglesFromSegments() {
        List<Segment> segments = segments();
        for( Integer i=0; i<segments.size()-1; i++ ) {
            for (Integer j=i+1; j<segments.size(); j++) {
                Segment firstSegment = segments.get(i);
                Segment secondSegment = segments.get(j);
                Optional<Point> intersection = firstSegment.intersection(secondSegment);
                intersection.ifPresent(point -> addAngle(firstSegment, secondSegment));
            }
        }
    }

    public List<Segment> segments() {
        return items.stream().filter(item -> item.type().equals(ItemType.SEGMENT)).map(item -> (Segment) item).collect(Collectors.toList());
    }

    private void discoverTrianglesFromSegments() {
        List<Point> points = points();
        for( Integer i=0; i<points.size()-1; i++ ) {
            for (Integer j=i+1; j<points.size(); j++) {
                for (Integer k=j+1; k<points.size(); k++) {
                    Point firstPoint = points.get(i);
                    Point secondoPoint = points.get(j);
                    Point thirdPoint = points.get(k);
                    String triangleName = firstPoint.name()+secondoPoint.name()+thirdPoint.name();
                    if (!findItemByName(triangleName).isPresent()) {
                        addTriangle(triangleName);
                    }
                }
            }
        }
    }

    private void assignChidrenToTriangles() {
        triangles().stream()
                .forEach(triangle -> {

                });
    }

    public List<Point> points() {
        return items.stream().filter(item -> item.type().equals(ItemType.POINT)).map(item -> (Point) item).collect(Collectors.toList());
    }

    public List<Angle> angles() {
        return items.stream().filter(item -> item.type().equals(ItemType.ANGLE)).map(item -> (Angle) item).collect(Collectors.toList());
    }

    public Optional<Item> findItemByName(String name) {
        return items.stream().filter(item -> item.aliases().contains(name)).findAny();
    }

    public Collection<Item> items() {
        return items;
    }

    public void extendSegmentFromPointToPoint(Segment segment, Point theNearestInnerPoint, Point theOuterPoint) {
        Point theFurthestInnerPoint = segment.otherPoint(theNearestInnerPoint);
        String straightLineName = theFurthestInnerPoint.name().toLowerCase() + theNearestInnerPoint.name().toLowerCase();
        StraightLine straightLine = straightLineFactory.build(straightLineName);
        straightLine.addOuterPointFromPoint(theOuterPoint, theNearestInnerPoint);
        items.add(straightLine);
    }

}
