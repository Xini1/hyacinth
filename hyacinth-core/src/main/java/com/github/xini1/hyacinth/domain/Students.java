package com.github.xini1.hyacinth.domain;

import com.github.xini1.hyacinth.usecase.PairsUseCase;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Students {

    private final Set<Student> all;

    private Students(Set<Student> all) {
        this.all = new TreeSet<>(all);
    }

    static Students from(Set<PairsUseCase.Group> groups) {
        return new Students(
                groups.stream()
                        .flatMap(Students::students)
                        .collect(Collectors.toSet())
        );
    }

    private static Stream<Student> students(PairsUseCase.Group group) {
        return group.studentNames()
                .stream()
                .map(name -> Student.with(name, group.name()));
    }

    Pair pair(Random random, PairCriteria pairCriteria) {
        var first = randomStudent(random);
        Student second;
        do {
            second = randomStudent(random);
        } while (pairCriteria.canNotBePaired(this, first, second));
        return new Pair(first, second);
    }

    private Student randomStudent(Random random) {
        var position = random.nextInt(all.size());
        var iterator = all.iterator();
        for (int i = 0; i < position; i++) {
            iterator.next();
        }
        return iterator.next();
    }

    Students without(Pair pair) {
        return pair.removePickedFrom(this);
    }

    Students without(Student student) {
        var copy = new HashSet<>(all);
        copy.remove(student);
        return new Students(copy);
    }

    boolean havePair() {
        return all.size() >= 2;
    }

    Student remaining() {
        return all.iterator().next();
    }

    boolean onlyOneLeft() {
        return all.size() == 1;
    }

    boolean haveMoreThanOneGroup() {
        var iterator = all.iterator();
        var student = iterator.next();
        while (iterator.hasNext()) {
            if (!student.isInSameGroupWith(iterator.next())) {
                return true;
            }
        }
        return false;
    }
}
