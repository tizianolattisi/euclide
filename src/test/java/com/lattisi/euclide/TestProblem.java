package com.lattisi.euclide;

import com.lattisi.euclide.items.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TestProblem {

    private static Problem problem;

    @BeforeEach
    public void initializeProblem() {
        problem = new Problem();
    }

    @Test
    public void testPointCreation() {
        problem.addPoint("A");
        Optional<Item> A = problem.findItemByName("A");
        Assertions.assertTrue(A.isPresent());
    }

    @Test
    public void testSegmentCreation() {
        problem.addSegment("AB", "l");
        Optional<Item> AB = problem.findItemByName("AB");
        Assertions.assertTrue(AB.isPresent());
    }

    @Test
    public void testStraightLineFromSegment() {
        problem.addSegment("AB");
        Segment AB = (Segment) problem.findItemByName("AB").get();
        problem.refresh();
        Point B = (Point) problem.findItemByName("B").get();
        Point C = problem.addPoint("C");
        problem.extendSegmentFromPointToPoint(AB, B, C);
        Optional<Item> optionalAb = problem.findItemByName("ab");
        Assertions.assertTrue(optionalAb.isPresent());
        Assertions.assertEquals(ItemType.STRAIGHT_LINE, optionalAb.get().type());
        StraightLine ab = (StraightLine) optionalAb.get();
        Assertions.assertTrue(ab.children().contains(C));
    }


    @Test
    public void testAngleDiscoverByRefresh() {
        problem.addSegment("AB", "l");
        problem.addSegment("BC");
        Optional<Item> itemNotPresent = problem.findItemByName("abc");
        Assertions.assertFalse(itemNotPresent.isPresent());
        problem.refresh();
        Optional<Item> abc = problem.findItemByName("abc");
        Assertions.assertTrue(abc.isPresent());
        Assertions.assertEquals(ItemType.ANGLE, abc.get().type());
    }

    @Test
    public void testTriangleDiscoverByRefresh() {
        problem.addSegment("AB", "l");
        problem.addSegment("BC");
        problem.addSegment("CA");
        Optional<Item> itemNotPresent = problem.findItemByName("ABC");
        Assertions.assertFalse(itemNotPresent.isPresent());
        problem.refresh();
        Optional<Item> ABC = problem.findItemByName("ABC");
        Assertions.assertTrue(ABC.isPresent());
        Assertions.assertEquals(ItemType.TRIANGLE, ABC.get().type());
    }

    @Test
    public void testTriangleCreation() {
        problem.addTriangle("ABC");
        Assertions.assertEquals(1, problem.items().size());
        problem.refresh();
        Assertions.assertEquals(3, problem.points().size());
        Assertions.assertEquals(3, problem.segments().size());
        Assertions.assertEquals(3, problem.angles().size());
        Assertions.assertEquals(10, problem.items().size());
        Assertions.assertTrue(problem.findItemByName("A").isPresent());
        Assertions.assertTrue(problem.findItemByName("B").isPresent());
        Assertions.assertTrue(problem.findItemByName("C").isPresent());
        Assertions.assertFalse(problem.findItemByName("D").isPresent());
        Assertions.assertTrue(problem.findItemByName("AB").isPresent());
        Assertions.assertTrue(problem.findItemByName("BC").isPresent());
        Assertions.assertTrue(problem.findItemByName("CA").isPresent());
        Assertions.assertTrue(problem.findItemByName("BA").isPresent());
        Assertions.assertTrue(problem.findItemByName("CB").isPresent());
        Assertions.assertTrue(problem.findItemByName("AC").isPresent());
        Assertions.assertFalse(problem.findItemByName("DC").isPresent());
        Assertions.assertTrue(problem.findItemByName("abc").isPresent());
        Assertions.assertTrue(problem.findItemByName("acb").isPresent());
        Assertions.assertTrue(problem.findItemByName("bac").isPresent());
        Assertions.assertTrue(problem.findItemByName("bca").isPresent());
        Assertions.assertTrue(problem.findItemByName("cab").isPresent());
        Assertions.assertTrue(problem.findItemByName("cba").isPresent());
        Assertions.assertFalse(problem.findItemByName("abd").isPresent());
    }

}
