package com.github.xini1.hyacinth.domain;

import java.util.Objects;

final class Group implements Comparable<Group> {

    private final String name;

    Group(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        var group = (Group) object;
        return Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Group group) {
        return name.compareTo(group.name);
    }
}
