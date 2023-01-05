package com.github.xini1.hyacinth.domain;

import com.github.xini1.hyacinth.usecase.PairsUseCase;
import com.github.xini1.hyacinth.usecase.exception.GroupHasNoName;
import com.github.xini1.hyacinth.usecase.exception.StudentHasNoName;

import java.util.Objects;

final class Student implements Comparable<Student> {

    private final String name;
    private final Group group;

    private Student(String name, Group group) {
        this.name = name;
        this.group = group;
    }

    static Student with(String name, String groupName) {
        if (name.isEmpty()) {
            throw new StudentHasNoName();
        }
        if (groupName.isEmpty()) {
            throw new GroupHasNoName();
        }
        return new Student(name, new Group(groupName));
    }

    PairsUseCase.Student dto() {
        return new PairsUseCase.Student(name, group.toString());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        var student = (Student) object;
        return Objects.equals(name, student.name) &&
                Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group);
    }

    @Override
    public int compareTo(Student student) {
        var comparisonByGroup = group.compareTo(student.group);
        if (comparisonByGroup == 0) {
            return name.compareTo(student.name);
        }
        return comparisonByGroup;
    }

    boolean isInSameGroupWith(Student student) {
        return group.equals(student.group);
    }
}
