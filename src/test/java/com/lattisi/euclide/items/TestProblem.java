package com.lattisi.euclide.items;

import com.lattisi.euclide.Problem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

public class TestProblem {

    private static Problem problem;

    @BeforeEach
    public void initializeProblem() {
        problem = new Problem();
    }

    @Test
    public void testItemsCreation() {
        problem.addSegment("AB", "l");
        Optional<Item> ab = problem.findItemByName("AB");
        Assertions.assertTrue(ab.isPresent());
    }

    @Test
    public void testAngleCreationByRefresh() {
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
    public void testTriangleCreation() {
        Triangle ABC = problem.addTriangle("ABC");
        Collection<Item> items = problem.items();
        for (Item item: items) {
            System.out.println(item);
        }
    }

}
