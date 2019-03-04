package com.lattisi.euclide.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTriangle {

    private static TriangleFactory triangleFactory;
    private static SegmentFactory segmentFactory;


    @BeforeEach
    public void initializeFactories() {
        triangleFactory = new TriangleFactory();
        segmentFactory = new SegmentFactory();
    }

    @Test
    public void testTriangleCreationFromName() {
        Triangle ABC = triangleFactory.build("ABC");
        Assertions.assertEquals("ABC", ABC.name());
        Assertions.assertEquals(ItemType.TRIANGLE, ABC.type());
    }

    @Test
    public void testTriangleContainsPoint() {
        Triangle ABC = triangleFactory.build("ABC");
        Assertions.assertTrue(ABC.contains(segmentFactory.build("AB")));
        Assertions.assertTrue(ABC.contains(segmentFactory.build("BC")));
        Assertions.assertTrue(ABC.contains(segmentFactory.build("CA")));
        Assertions.assertTrue(ABC.contains(segmentFactory.build("BC")));
        Assertions.assertTrue(ABC.contains(segmentFactory.build("CB")));
        Assertions.assertTrue(ABC.contains(segmentFactory.build("AC")));
    }

    @Test
    public void testSegmentDoesNotContainsPoint() {
        Triangle ABC = triangleFactory.build("ABC");
        Assertions.assertFalse(ABC.contains(segmentFactory.build("CD")));
    }

}
