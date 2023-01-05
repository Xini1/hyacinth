package com.github.xini1.hyacinth.domain;

import com.github.xini1.hyacinth.usecase.PairsUseCase;

final class Pair {

    private final Student first;
    private final Student second;

    Pair(Student first, Student second) {
        this.first = first;
        this.second = second;
    }

    PairsUseCase.Pair dto() {
        return new PairsUseCase.Pair(first.dto(), second.dto());
    }

    Students removePickedFrom(Students students) {
        return students.without(first).without(second);
    }
}
