package com.github.xini1.hyacinth;

import com.github.xini1.hyacinth.usecase.PairsUseCase;

final class OutputFormatter {

    String format(PairsUseCase.Result result, long seed) {
        var builder = new StringBuilder("seed: ").append(seed).append(System.lineSeparator());
        appendPair(builder, result);
        appendRemaining(builder, result);
        return builder.toString().trim();
    }

    private void appendRemaining(StringBuilder builder, PairsUseCase.Result result) {
        if (result.hasNotStudentWithoutPair()) {
            return;
        }
        builder.append("remaining: ");
        appendStudent(builder, result.remaining());
        builder.append(System.lineSeparator());
    }

    private void appendPair(StringBuilder builder, PairsUseCase.Result result) {
        if (result.pairs().isEmpty()) {
            return;
        }
        builder.append("pairs:").append(System.lineSeparator());
        for (int i = 0; i < result.pairs().size(); i++) {
            var pair = result.pairs().get(i);
            builder.append(i + 1).append(") ");
            appendStudent(builder, pair.first());
            builder.append(" vs ");
            appendStudent(builder, pair.second());
            builder.append(System.lineSeparator());
        }
    }

    private void appendStudent(StringBuilder builder, PairsUseCase.Student student) {
        builder.append(student.name())
                .append(" (").append(student.groupName()).append(")");
    }
}
