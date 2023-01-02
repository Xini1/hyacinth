package com.github.xini1.usecase;

import java.util.List;
import java.util.Set;

public interface PollsUseCase {

    List<Poll> polls(Set<Group> groups, long seed);

    record Poll(PickedStudent first, PickedStudent second) {

        public Poll(PickedStudent student) {
            this(student, null);
        }
    }

    record PickedStudent(String name, String groupName) {}

    record Group(String name, Set<String> studentNames) {

        public Group(String name, Set<String> studentNames) {
            this.name = name;
            this.studentNames = Set.copyOf(studentNames);
        }
    }
}
