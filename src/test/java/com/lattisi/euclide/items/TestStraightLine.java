package com.lattisi.euclide.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStraightLine {

    private static StraightLineFactory straightLineFactory;
    private static PointFactory pointFactory;

    @BeforeEach
    public void initializeFactories() {
        straightLineFactory = new StraightLineFactory();
        pointFactory = new PointFactory();
    }

    @Test
    public void testStraightLineFromName() {
        StraightLine ab = straightLineFactory.build("ab");
        Assertions.assertEquals("ab", ab.name());
        Assertions.assertEquals(ItemType.STRAIGHT_LINE, ab.type());
    }

    @Test
    public void testStraightLineContainsPoints() {
        StraightLine ab = straightLineFactory.build("ab");
        Point A = pointFactory.build("A");
        Point B = pointFactory.build("B");
        Assertions.assertTrue(ab.contains(A));
        Assertions.assertTrue(ab.contains(B));
    }

    @Test
    public void testStraightLineDoesNotContainsPoints() {
        StraightLine ab = straightLineFactory.build("ab");
        Point C = pointFactory.build("C");
        Assertions.assertFalse(ab.contains(C));
    }

}
