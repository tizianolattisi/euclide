package com.lattisi.euclide.items;

import com.lattisi.euclide.AliasesUtility;

import java.util.Collection;
import java.util.Objects;

public abstract class AbstractItem implements Item {

    protected String name;

    public AbstractItem(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Collection<String> aliases() {
        return AliasesUtility.computeAliases(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractItem that = (AbstractItem) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ",type='" + type() + '\'' +
                '}';
    }

}
