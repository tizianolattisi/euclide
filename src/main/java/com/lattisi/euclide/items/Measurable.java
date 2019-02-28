package com.lattisi.euclide.items;

import java.util.Optional;

public interface Measurable extends Item {

    Optional<String> measure();
    void assignMeasure(String measure);
    Boolean isCongruentTo(Measurable item) throws ItemWithoutMeasureException;

}
