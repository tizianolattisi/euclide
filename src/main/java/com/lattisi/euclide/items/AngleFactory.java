package com.lattisi.euclide.items;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AngleFactory implements ItemFactory<Angle> {

    @Override
    public Angle build(String name) {
        return null;
    }

    public Angle build(Point startPoint, Point centralPoint, Point endPoint) {
        String name = startPoint.name().toLowerCase()+centralPoint.name().toLowerCase()+endPoint.name().toLowerCase();
        Angle angle = new Angle(name);
        return angle;
    }

    public Angle build(Segment firstSegment, Segment secondSegment) {
        Optional<Point> intersection = firstSegment.intersection(secondSegment);
        assert intersection.isPresent();
        Point centralPoint = intersection.get();
        List<Point> pointsOfTheAngle = Arrays.asList(firstSegment.otherPoint(centralPoint), centralPoint, secondSegment.otherPoint(centralPoint));
        return build(pointsOfTheAngle.get(0), pointsOfTheAngle.get(1), pointsOfTheAngle.get(2));
    }

}
