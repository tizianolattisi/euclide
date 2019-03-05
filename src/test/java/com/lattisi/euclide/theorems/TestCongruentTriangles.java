package com.lattisi.euclide.theorems;

import com.lattisi.euclide.Problem;
import com.lattisi.euclide.items.Segment;
import com.lattisi.euclide.items.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCongruentTriangles {

    private static Problem problem;

    private static final String L1 = "l1 measure";
    private static final String L2 = "l2 measure";
    private static final String L3 = "l3 measure";

    @BeforeEach
    public void initializeProblem() {
        problem = new Problem();
    }

    @Test
    public void testSideSideSide() {
        Triangle ABC = problem.addTriangle("ABC");
        Triangle DEF = problem.addTriangle("DEF");
        problem.refresh();
        assignMeasureToSegment("AB", L1);
        assignMeasureToSegment("BC", L2);
        assignMeasureToSegment("CA", L3);
        assignMeasureToSegment("DE", L3);
        assignMeasureToSegment("EF", L2);
        assignMeasureToSegment("DF", L1);
        Assertions.assertTrue(CongruentTriangles.SideSideSide.apply(ABC, DEF));
    }

    @Test
    public void testFailSideSideSide() {
        Triangle ABC = problem.addTriangle("ABC");
        Triangle DEF = problem.addTriangle("DEF");
        problem.refresh();
        assignMeasureToSegment("AB", L1);
        assignMeasureToSegment("BC", L2);
        assignMeasureToSegment("CA", L3);
        assignMeasureToSegment("DE", L3);
        assignMeasureToSegment("EF", L2);
        assignMeasureToSegment("DF", L2);
        Assertions.assertFalse(CongruentTriangles.SideSideSide.apply(ABC, DEF));
    }

    private void assignMeasureToSegment(String segmentName, String measure) {
        Segment AB = (Segment) problem.findItemByName(segmentName).get();
        AB.assignMeasure(measure);
    }

}
