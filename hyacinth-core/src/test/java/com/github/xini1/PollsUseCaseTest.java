package com.github.xini1;

import com.github.xini1.domain.HyacinthCore;
import com.github.xini1.usecase.PollsUseCase;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PollsUseCaseTest {

    private final PollsUseCase useCase = new HyacinthCore().pollsUseCase();
    private final long seed = 1;

    @Test
    void givenNoGroups_whenRequestingPolls_thenNoPolls() {
        assertThat(useCase.polls(Set.of(), seed)).isEmpty();
    }
}