package org.example.qaassignment.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileUtils {

    private FileUtils() {

    }

    public static void emptyFolder(Path folder) throws IOException {
        if (!Files.exists(folder)) {
            return;
        }

        try (var files = Files.list(folder)) {
            for (Path file : files.toList()) {
                Files.deleteIfExists(file);
            }
        }
    }

}
