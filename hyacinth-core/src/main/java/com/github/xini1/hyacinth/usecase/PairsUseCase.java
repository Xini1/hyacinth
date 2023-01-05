package com.github.xini1.hyacinth.usecase;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface PairsUseCase {

    Result pairs(Set<Group> groups, long seed);

    record Result(List<Pair> pairs, Student remaining) {

        public Result(List<Pair> pairs, Student remaining) {
            this.pairs = List.copyOf(pairs);
            this.remaining = remaining;
        }

        public Result(List<Pair> pairs) {
            this(pairs, null);
        }

        public Result(Student remaining) {
            this(List.of(), remaining);
        }

        public Result() {
            this(List.of(), null);
        }

        public boolean hasNotStudentWithoutPair() {
            return remaining == null;
        }
    }

    record Pair(Student first, Student second) {}

    record Student(String name, String groupName) {}

    record Group(String name, Set<String> studentNames) {

        public Group(String name, Set<String> studentNames) {
            this.name = name;
            this.studentNames = studentNames.stream()
                    .map(studentName -> Objects.requireNonNullElse(studentName, ""))
                    .collect(Collectors.toUnmodifiableSet());
        }
    }
}
