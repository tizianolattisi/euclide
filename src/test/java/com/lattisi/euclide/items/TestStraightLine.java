package com.lattisi.euclide.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void testAddRightOuterPoint() {
        StraightLine ab = straightLineFactory.build("ab");
        Point A = new Point("A");
        Point B = new Point("B");
        Point C = pointFactory.build("C");
        ab.addOuterPointFromPoint(C, B);
        List<Point> listOfPoints = listOfOrderedPoints(ab);
        Point lastPoint = listOfPoints.get(listOfPoints.size() - 1);
        Assertions.assertEquals(C, lastPoint);
    }

    private List<Point> listOfOrderedPoints(StraightLine ab) {
        return ab.children().stream().map(item -> (Point) item).collect(Collectors.toList());
    }

    @Test
    public void testAddLeftOuterPoint() {
        StraightLine ab = straightLineFactory.build("ab");
        Point Z = pointFactory.build("Z");
        Point A = new Point("A");
        Point B = new Point("B");
        ab.addOuterPointFromPoint(Z, A);
        List<Point> listOfPoints = listOfOrderedPoints(ab);
        Point firstPoint = listOfPoints.get(0);
        Assertions.assertEquals(Z, firstPoint);
    }

}
