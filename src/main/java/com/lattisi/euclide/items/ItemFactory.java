package com.lattisi.euclide.items;


public interface ItemFactory<T> {
    T build(String name);
}
