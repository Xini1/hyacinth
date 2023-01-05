package com.github.xini1.hyacinth.domain;

final class StudentsFromDifferentGroups implements PairCriteria {

    @Override
    public boolean canNotBePaired(Students students, Student first, Student second) {
        return students.haveMoreThanOneGroup() && first.isInSameGroupWith(second);
    }
}
