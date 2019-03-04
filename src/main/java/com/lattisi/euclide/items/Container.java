package com.lattisi.euclide.items;

import java.util.Collection;

public interface Container {

    Collection<? extends Item> children();
    Boolean contains(Item item);

}
