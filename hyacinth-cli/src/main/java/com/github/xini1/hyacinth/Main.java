package com.github.xini1.hyacinth;

import com.github.xini1.hyacinth.domain.HyacinthCore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

final class Main {

    private static final int FILE_WITH_GROUPS = 0;
    private static final int SEED = 1;

    public static void main(String[] args) {
        try (var reader = Files.newBufferedReader(pathToFileWithGroups(args))) {
            var seed = seed(args);
            System.out.println(
                    new OutputFormatter()
                            .format(
                                    new HyacinthCore()
                                            .pairsUseCase()
                                            .pairs(
                                                    new Gson()
                                                            .fromJson(reader, new TypeToken<>() {}),
                                                    seed
                                            ),
                                    seed
                            )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Path pathToFileWithGroups(String[] args) {
        if (args.length < 1) {
            throw new PathToFileWithGroupsWasExpected();
        }
        return Paths.get(args[FILE_WITH_GROUPS]);
    }

    private static long seed(String[] args) {
        if (args.length < 2) {
            return ThreadLocalRandom.current().nextInt();
        }
        return Long.parseLong(args[SEED]);
    }
}
