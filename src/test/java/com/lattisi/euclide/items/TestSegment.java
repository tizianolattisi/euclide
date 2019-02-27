package com.lattisi.euclide.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestSegment {

    private static SegmentFactory segmentFactory;

    @BeforeEach
    public void initializeFactories() {
        segmentFactory = new SegmentFactory();
    }

    @Test
    public void testSegmentCreation() {
        Segment AB = segmentFactory.build("AB");
        Assertions.assertEquals("AB", AB.name());
        testTheNameOfThePoints(AB);
    }

    private void testTheNameOfThePoints(Segment AB) {
        Set<Point> points = AB.points();
        List<String> pointNames = points.stream().map(point -> point.name()).collect(Collectors.toList());
        Assertions.assertTrue(pointNames.contains("A"));
        Assertions.assertTrue(pointNames.contains("B"));
    }


}
