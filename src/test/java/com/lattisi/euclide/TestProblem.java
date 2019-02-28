package com.lattisi.euclide;

import com.lattisi.euclide.items.Item;
import com.lattisi.euclide.items.ItemType;
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
    private void testItemsCreation() {
        problem.addSegment("AB", "l");
        Optional<Item> ab = problem.findItemByName("AB");
        Assertions.assertTrue(ab.isPresent());
    }

    @Test
    private void testAngleCreationByRefresh() {
        problem.addSegment("AB", "l");
        problem.addPoint("BC");
        Optional<Item> itemNotPresent = problem.findItemByName("abc");
        Assertions.assertFalse(itemNotPresent.isPresent());
        problem.refresh();
        Optional<Item> abc = problem.findItemByName("abc");
        Assertions.assertFalse(abc.isPresent());
        Assertions.assertEquals(ItemType.ANGLE, abc.get().type());
    }

}
