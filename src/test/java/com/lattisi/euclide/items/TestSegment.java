package com.lattisi.euclide.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestSegment {

    private static SegmentFactory segmentFactory;
    private static PointFactory pointFactory;

    @BeforeEach
    public void initializeFactories() {
        segmentFactory = new SegmentFactory();
        pointFactory = new PointFactory();
    }

    @Test
    public void testSegmentCreationFromName() {
        Segment AB = segmentFactory.build("AB");
        Assertions.assertEquals("AB", AB.name());
        Assertions.assertEquals(ItemType.SEGMENT, AB.type());
        testTheNameOfThePoints(AB);
    }

    private void testTheNameOfThePoints(Segment AB) {
        List<String> pointNames = AB.children().stream().map(point -> point.name()).collect(Collectors.toList());
        Assertions.assertTrue(pointNames.contains("A"));
        Assertions.assertTrue(pointNames.contains("B"));
    }

    @Test
    public void testSegmentNameAlias() {
        Segment CD = segmentFactory.build("CD");
        Collection<String> aliases = CD.aliases();
        Assertions.assertEquals(2, aliases.size());
        Assertions.assertTrue(aliases.contains("CD"));
        Assertions.assertTrue(aliases.contains("DC"));
    }

    @Test
    public void testMeasurableSegment() {
        Segment EF = segmentFactory.build("EF");
        Assertions.assertFalse(EF.measure().isPresent());
        EF.assignMeasure("l");
        Assertions.assertTrue(EF.measure().isPresent());
        Assertions.assertEquals("l", EF.measure().get());
    }

    @Test
    public void testItemWithoutException() {
        Segment GH = segmentFactory.build("GH");
        Segment IL = segmentFactory.build("IL");
        GH.assignMeasure("l");
        ItemWithoutMeasureException exception = Assertions.assertThrows(ItemWithoutMeasureException.class, () -> GH.isCongruentTo(IL));
        Assertions.assertEquals("Unable to compare items without measure.", exception.getMessage());
    }

    @Test
    public void testCongruentSegments() throws ItemWithoutMeasureException {
        Segment MN = segmentFactory.build("MN");
        Segment OP = segmentFactory.build("OP");
        MN.assignMeasure("l");
        OP.assignMeasure("l");
        Assertions.assertTrue(MN.isCongruentTo(OP));
        Assertions.assertTrue(OP.isCongruentTo(MN));
    }

    @Test
    public void testIncongruentSegments() throws ItemWithoutMeasureException {
        Segment QR = segmentFactory.build("QR");
        Segment ST = segmentFactory.build("ST");
        QR.assignMeasure("l1");
        ST.assignMeasure("l2");
        Assertions.assertFalse(QR.isCongruentTo(ST));
        Assertions.assertFalse(ST.isCongruentTo(QR));
    }

    @Test
    public void testSegmentsIntersectionPresent() {
        Segment AB = segmentFactory.build("AB");
        Segment BC = segmentFactory.build("BC");
        Optional<Point> intersection = AB.intersection(BC);
        Assertions.assertTrue(intersection.isPresent());
        Assertions.assertEquals("B", intersection.get().name());
    }

    @Test
    public void testSegmentsIntersectionNotPresent() {
        Segment AB = segmentFactory.build("AB");
        Segment CD = segmentFactory.build("CD");
        Optional<Point> intersection = AB.intersection(CD);
        Assertions.assertFalse(intersection.isPresent());
    }

    @Test
    public void testSegmentContainsPoint() {
        Segment AB = segmentFactory.build("AB");
        Point A = pointFactory.build("A");
        Point B = pointFactory.build("B");
        Assertions.assertTrue(AB.contains(A));
        Assertions.assertTrue(AB.contains(B));
    }

    @Test
    public void testSegmentDoesNotContainsPoint() {
        Segment AB = segmentFactory.build("AB");
        Point C = pointFactory.build("C");
        Assertions.assertFalse(AB.contains(C));
    }

}
