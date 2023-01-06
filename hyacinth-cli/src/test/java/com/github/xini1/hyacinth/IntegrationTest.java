package com.github.xini1.hyacinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

final class IntegrationTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        var printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        System.setErr(printStream);
    }

    @Test
    void givenFileAndSeedAreProvided_whenExecuteMain_thenPrintPairs() {
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
        Main.main(new String[]{"src/test/resources/groups.json"});

        assertThat(outputStream.toString()).contains("seed:", "pairs:", "1)", "2)", "remaining");
    }

    @Test
    void givenNoArgumentsAreProvided_whenExecuteMain_thenPrintPathToFileWithGroupsWasExpected() {
        Main.main(new String[0]);

        assertThat(outputStream.toString()).contains("PathToFileWithGroupsWasExpected");
    }
}
