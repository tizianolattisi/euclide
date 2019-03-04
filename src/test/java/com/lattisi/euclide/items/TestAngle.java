package com.lattisi.euclide.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAngle {

    private static AngleFactory angleFactory;
    private static PointFactory pointFactory;
    private static SegmentFactory segmentFactory;

    @BeforeEach
    public void initializeFactories() {
        angleFactory = new AngleFactory();
        pointFactory = new PointFactory();
        segmentFactory = new SegmentFactory();
    }

    @Test
    public void testAngleCreationFromName() {
        Angle abc = angleFactory.build("abc");
        Assertions.assertEquals("abc", abc.name());
    }

    @Test
    public void testAngleCreationFromPoints() {
        Point A = pointFactory.build("A");
        Point B = pointFactory.build("B");
        Point C = pointFactory.build("C");
        Angle abc = angleFactory.build(A, B, C);
        Assertions.assertEquals("abc", abc.name());
    }

    @Test void testAngleFactoryFromSegments() {
        Segment AB = segmentFactory.build("AB");
        Segment BC = segmentFactory.build("BC");
        Angle abc = angleFactory.build(AB, BC);
        Assertions.assertEquals("abc", abc.name());
    }

}
