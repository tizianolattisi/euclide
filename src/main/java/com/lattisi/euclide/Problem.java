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

    private Collection<Item> items = new HashSet<>();

    public void addPoint(String name) {
        items.add(pointFactory.build(name));
    }

    public void addSegment(String name, String measure) {
        Segment segment = segmentFactory.build(name);
        segment.assignMeasure(measure);
        items.add(segment);
    }

    public void addAngle(Segment firstSegment, Segment secondSegment) {
        Angle angle = angleFactory.build(firstSegment, secondSegment);
        items.add(angle);
    }

    public void addSegment(String name) {
        items.add(segmentFactory.build(name));
    }

    public void refresh() {
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

}
