package com.github.xini1.hyacinth;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

final class IntegrationTest {

    @Test
    void givenFileAndSeedAreProvided_whenExecuteMain_thenPrintPairs() {
        var outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[]{"src/test/resources/groups.json", "1"});

        assertThat(outputStream)
                .hasToString("""
                        seed: 1
                        pairs:
                        1) Alex (blue) vs John (red)
                        2) Max (green) vs Matt (red)
                        remaining: Ann (green)
                        """);
    }

    @Test
    void givenOnlyFileIsProvided_whenExecuteMain_thenPrintRandomSeed() {
        var outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[]{"src/test/resources/groups.json"});

        assertThat(outputStream.toString())
                .contains("seed:", "pairs:", "1)", "2)", "remaining");
    }
}
