package com.github.xini1.domain;

import com.github.xini1.usecase.PollsUseCase;

import java.util.List;
import java.util.Set;

class PollService implements PollsUseCase {

    @Override
    public List<Poll> polls(Set<Group> groups, long seed) {
        return List.of();
    }
}
