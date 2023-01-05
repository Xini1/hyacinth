package com.github.xini1;

import com.github.xini1.hyacinth.domain.HyacinthCore;
import com.github.xini1.hyacinth.usecase.PairsUseCase;
import com.github.xini1.hyacinth.usecase.exception.GroupHasNoName;
import com.github.xini1.hyacinth.usecase.exception.StudentHasNoName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

final class PairsUseCaseTest {

    private final PairsUseCase useCase = new HyacinthCore().pairsUseCase();
    private final long seed = 1;

    @Test
    void givenNoStudents_whenRequestingPairs_thenNoPairs() {
        assertThat(useCase.pairs(Set.of(), seed)).isEqualTo(new PairsUseCase.Result(List.of()));
    }

    @Test
    void givenOneStudent_whenRequestingPolls_thenOneStudentRemaining() {
        assertThat(useCase.pairs(Set.of(new PairsUseCase.Group("group", Set.of("Max"))), seed))
                .isEqualTo(new PairsUseCase.Result(new PairsUseCase.Student("Max", "group")));
    }

    @Test
    void givenTwoStudent_whenRequestingPairs_thenPairContainsTwoStudents() {
        assertThat(useCase.pairs(Set.of(new PairsUseCase.Group("group", Set.of("Max", "Ann"))), seed))
                .isEqualTo(
                        new PairsUseCase.Result(
                                List.of(
                                        new PairsUseCase.Pair(
                                                new PairsUseCase.Student("Max", "group"),
                                                new PairsUseCase.Student("Ann", "group")
                                        )
                                )
                        )
                );
    }

    @Test
    void givenThreeStudent_whenRequestingPairs_thenPairContainsTwoStudentsAndOneRemaining() {
        assertThat(useCase.pairs(Set.of(new PairsUseCase.Group("group", Set.of("Max", "Ann", "Matt"))), seed))
                .isEqualTo(
                        new PairsUseCase.Result(
                                List.of(
                                        new PairsUseCase.Pair(
                                                new PairsUseCase.Student("Ann", "group"),
                                                new PairsUseCase.Student("Matt", "group")
                                        )
                                ),
                                new PairsUseCase.Student("Max", "group")
                        )
                );
    }

    @Test
    void givenTwoGroups_whenRequestingPairs_thenPairContainsStudentsFromDifferentGroups() {
        assertThat(
                useCase.pairs(
                        Set.of(
                                new PairsUseCase.Group("group1", Set.of("Max", "Ann")),
                                new PairsUseCase.Group("group2", Set.of("Matt"))
                        ),
                        seed
                )
        )
                .isEqualTo(
                        new PairsUseCase.Result(
                                List.of(
                                        new PairsUseCase.Pair(
                                                new PairsUseCase.Student("Ann", "group1"),
                                                new PairsUseCase.Student("Matt", "group2")
                                        )
                                ),
                                new PairsUseCase.Student("Max", "group1")
                        )
                );
    }

    @Test
    void givenEmptyStudentName_whenRequestingPairs_thenStudentHasNoNameThrown() {
        var groups = Set.of(new PairsUseCase.Group("group", Set.of("")));

        assertThatThrownBy(() -> useCase.pairs(groups, seed)).isInstanceOf(StudentHasNoName.class);
    }

    @Test
    void givenEmptyGroupName_whenRequestingPairs_thenGroupHasNoNameThrown() {
        var groups = Set.of(new PairsUseCase.Group("", Set.of("Max")));

        assertThatThrownBy(() -> useCase.pairs(groups, seed)).isInstanceOf(GroupHasNoName.class);
    }
}