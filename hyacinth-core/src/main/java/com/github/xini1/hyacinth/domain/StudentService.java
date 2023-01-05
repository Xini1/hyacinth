package com.github.xini1.hyacinth.domain;

import com.github.xini1.hyacinth.usecase.PairsUseCase;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

final class StudentService implements PairsUseCase {

    private final PairCriteria pairCriteria;

    StudentService(PairCriteria pairCriteria) {
        this.pairCriteria = pairCriteria;
    }

    @Override
    public Result pairs(Set<Group> groups, long seed) {
        var students = Students.from(groups);
        var random = new Random(seed);
        var pairs = new ArrayList<Pair>();
        while (students.havePair()) {
            var pair = students.pair(random, pairCriteria);
            pairs.add(pair.dto());
            students = students.without(pair);
        }
        if (students.onlyOneLeft()) {
            return new Result(pairs, students.remaining().dto());
        }
        return new Result(pairs);
    }
}
