package com.lattisi.euclide.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;


public class TestPoint {

    private static PointFactory pointFactory;

    @BeforeEach
    public void initializeFactories() {
        pointFactory = new PointFactory();
    }

    @Test
    public void testPointCreation() {
        Point A = pointFactory.build("A");
        Assertions.assertEquals("A", A.name());
    }

    @Test
    public void testPointAliases() {
        Point A = pointFactory.build("B");
        Collection<String> aliases = A.aliases();
        Assertions.assertEquals(1, aliases.size());
        Assertions.assertEquals("B", aliases.iterator().next());
    }



}
