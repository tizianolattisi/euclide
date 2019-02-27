package com.lattisi.euclide.items;

import java.util.Collection;

public interface Item {
    String name();
    ItemType type();
    Collection<String> aliases();
}
