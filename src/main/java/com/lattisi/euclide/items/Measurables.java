package com.lattisi.euclide.items;

import java.util.Optional;

public interface Measurables extends Item {

    Optional<String> measure();
    void assignMeasure(String measure);
    Boolean isCongruentTo(Measurables item);

}
