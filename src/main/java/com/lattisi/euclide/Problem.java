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
        discoverAnglesFromSegments();
    }

    private void discoverAnglesFromSegments() {
        List<Segment> segments = items.stream().filter(item -> item.type().equals(ItemType.SEGMENT)).map(item -> (Segment) item).collect(Collectors.toList());
        for( Integer i=0; i<segments.size()-1; i++ ) {
            for (Integer j = i + 1; j < segments.size(); j++) {
                Segment firstSegment = segments.get(i);
                Segment secondSegment = segments.get(j);
                Optional<Point> intersection = firstSegment.intersection(secondSegment);
                intersection.ifPresent(point -> addAngle(firstSegment, secondSegment));
            }
        }
    }

    public Optional<Item> findItemByName(String name) {
        return items.stream().filter(item -> item.name().equals(name)).findAny();
    }

    public Collection<Item> items() {
        return items;
    }
}
