package com.github.xini1.hyacinth.domain;

import com.github.xini1.hyacinth.usecase.PairsUseCase;

public final class HyacinthCore {

    public PairsUseCase pairsUseCase() {
        return new StudentService(new StudentsFromDifferentGroups());
    }
}
