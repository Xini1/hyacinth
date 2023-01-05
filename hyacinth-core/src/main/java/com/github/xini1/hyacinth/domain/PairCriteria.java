package com.github.xini1.hyacinth.domain;

interface PairCriteria {

    boolean canNotBePaired(Students students, Student first, Student second);
}
