package com.lattisi.euclide.theorems;

import com.lattisi.euclide.items.Segment;
import com.lattisi.euclide.items.Triangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;


public class CongruentTriangles {

    public static BiFunction<Triangle, Triangle, Boolean> SideSideSide = (firstTriangle, secondTriangle) -> {
        List<String> lengthOfTheFirstTriangle = getSegmentMeasures(firstTriangle);
        if (lengthOfTheFirstTriangle.size()<3){
            return false;
        }
        List<String> lengthsOfTheSecondTriangle = getSegmentMeasures(secondTriangle);
        if (lengthsOfTheSecondTriangle.size()<3){
            return false;
        }
        Collections.sort(lengthOfTheFirstTriangle);
        Collections.sort(lengthsOfTheSecondTriangle);
        if (lengthOfTheFirstTriangle.equals(lengthsOfTheSecondTriangle)){
            return true;
        }
        return false;
    };

    private static List<String> getSegmentMeasures(Triangle triangle){
        List<String> metrics = new ArrayList<>();
        for (Segment segment: triangle.segments()){
            if (segment.measure().isPresent()){
                metrics.add(segment.measure().get());
            }
        }
        return metrics;
    }

}
