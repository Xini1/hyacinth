package com.github.xini1.domain;

import com.github.xini1.usecase.PollsUseCase;

public class HyacinthCore {

    public PollsUseCase pollsUseCase() {
        return new PollService();
    }
}
