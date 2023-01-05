package com.github.xini1.hyacinth;

import com.github.xini1.hyacinth.usecase.PairsUseCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OutputFormatterTest {

    private final OutputFormatter outputFormatter = new OutputFormatter();

    @Test
    void givenEmptyResult_whenFormat_thenStringContainsOnlySeed() {
        assertThat(outputFormatter.format(new PairsUseCase.Result(), 1))
                .isEqualToIgnoringNewLines("seed: 1");
    }

    @Test
    void givenResultContainsPair_whenFormat_thenStringContainsTwoStudents() {
        assertThat(
                outputFormatter.format(
                        new PairsUseCase.Result(
                                List.of(
                                        new PairsUseCase.Pair(
                                                new PairsUseCase.Student("Max", "group"),
                                                new PairsUseCase.Student("Ann", "group")
                                        )
                                )
                        ),
                        1
                )
        )
                .isEqualToIgnoringNewLines("""
                        seed: 1
                        pairs:
                        1) Max (group) vs Ann (group)
                        """);
    }

    @Test
    void givenResultContainsTwoPair_whenFormat_thenPairsAreNumbered() {
        assertThat(
                outputFormatter.format(
                        new PairsUseCase.Result(
                                List.of(
                                        new PairsUseCase.Pair(
                                                new PairsUseCase.Student("Max", "group"),
                                                new PairsUseCase.Student("Ann", "group")
                                        ),
                                        new PairsUseCase.Pair(
                                                new PairsUseCase.Student("Matt", "group"),
                                                new PairsUseCase.Student("John", "group")
                                        )
                                )
                        ),
                        1
                )
        )
                .isEqualToIgnoringNewLines("""
                        seed: 1
                        pairs:
                        1) Max (group) vs Ann (group)
                        2) Matt (group) vs John (group)
                        """);
    }

    @Test
    void givenResultContainsRemainingStudent_whenFormat_thenStringContainsRemainingStudent() {
        assertThat(
                outputFormatter.format(
                        new PairsUseCase.Result(new PairsUseCase.Student("Max", "group")),
                        1
                )
        )
                .isEqualTo("""
                        seed: 1
                        remaining: Max (group)\
                        """);
    }

    @Test
    void givenResultContainsPairsAndRemainingStudent_whenFormat_thenStringHasThemAll() {
        assertThat(
                outputFormatter.format(
                        new PairsUseCase.Result(
                                List.of(
                                        new PairsUseCase.Pair(
                                                new PairsUseCase.Student("Max", "group"),
                                                new PairsUseCase.Student("Ann", "group")
                                        )
                                ),
                                new PairsUseCase.Student("Matt", "group")
                        ),
                        1
                )
        )
                .isEqualToIgnoringNewLines("""
                        seed: 1
                        pairs:
                        1) Max (group) vs Ann (group)
                        remaining: Matt (group)
                        """);
    }
}