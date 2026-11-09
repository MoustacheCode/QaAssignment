package org.example.qaassignment.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileUtils {

    private FileUtils() {

    }

    public static void emptyFolder(Path folder) throws IOException {
        // If the folder does not exist there is nothing to clean
        if (!Files.exists(folder)) {
            return;
        }
        // Get everything currently inside the folder
        try (var files = Files.list(folder)) {
            // Delete each leftover test file but leave the folder itself in place
            for (Path file : files.toList()) {
                Files.deleteIfExists(file);
            }
        }
    }

}
