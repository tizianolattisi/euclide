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

    private Collection<Item> items = new HashSet<>();

    public Point addPoint(String name) {
        Point point = pointFactory.build(name);
        items.add(point);
        return point;
    }

    public Segment addSegment(String name) {
        Segment segment = segmentFactory.build(name);
        items.add(segment);
        return segment;
    }

    public Segment addSegment(String name, String measure) {
        Segment segment = segmentFactory.build(name);
        segment.assignMeasure(measure);
        items.add(segment);
        return segment;
    }

    public Angle addAngle(Segment firstSegment, Segment secondSegment) {
        Angle angle = angleFactory.build(firstSegment, secondSegment);
        items.add(angle);
        return angle;
    }

    public Triangle addTriangle(String name) {
        Triangle triangle = triangleFactory.build(name);
        items.add(triangle);
        return triangle;
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
                                    addSegment(segment.name());
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
                            .forEach(point -> {
                                if (!findItemByName(point.name()).isPresent()) {
                                    addPoint(point.name());
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

}
