package org.example.qaassignment.support;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class TestFileFactory {

    private TestFileFactory() {
    }

    public record ArchiveEntry(String fileName, String type) {
    }

        // Creates a ZIP folder for each of the Tests to be ran against
    public static Path createZip(
            Path inputFolder,
            String zipFileName,
            List<ArchiveEntry> entries
    ) throws IOException {

        Files.createDirectories(inputFolder);

        Path zipPath = inputFolder.resolve(zipFileName);

        try (ZipOutputStream output =
                     new ZipOutputStream(Files.newOutputStream(zipPath))) {

            for (ArchiveEntry entry : entries) {

                output.putNextEntry(
                        new ZipEntry(entry.fileName())
                );

                output.write(
                        contentFor(entry.type())
                );

                output.closeEntry();
            }
        }

        return zipPath;
    }

        // Creates Empty ZIP for the Empty ZIP file test
    public static Path createEmptyZip(
            Path inputFolder,
            String zipFileName
    ) throws IOException {

        Files.createDirectories(inputFolder);

        Path zipPath = inputFolder.resolve(zipFileName);

        try (ZipOutputStream ignored =
                     new ZipOutputStream(Files.newOutputStream(zipPath))) {
            // Intentionally contains no files.
        }

        return zipPath;
    }

    public static Path createInvalidZip(Path inputFolder,
                                        String zipFileName
    ) throws IOException {

        Files.createDirectories(inputFolder);
        Path zipPath = inputFolder.resolve(zipFileName);

        Files.writeString(zipPath, "This is not a valid ZIP file");

        return zipPath;
    }

    public static Path createUnsafeZip(Path inputFolder,
                                       String zipFileName)
            throws IOException {

        Files.createDirectories(inputFolder);
        Path zipPath = inputFolder.resolve(zipFileName);

        try (ZipOutputStream unsafe = new ZipOutputStream(
                Files.newOutputStream(zipPath))) {

            ZipEntry unsafeEntry = new ZipEntry("../unsafe.pdf");

            unsafe.putNextEntry(unsafeEntry);

            unsafe.write ("unsafe test content".getBytes());

            unsafe.closeEntry();
        }

        return zipPath;

    }


    private static byte[] contentFor(String type) {
            // Switch Statement for each of the Required filetypes to be used in the tests
        return switch (type.toUpperCase(Locale.ROOT)) {

            case "PDF" -> "%PDF-1.4\n% test PDF\n%%EOF"
                    .getBytes(StandardCharsets.ISO_8859_1);

            case "JPEG" -> new byte[]{
                    (byte) 0xFF, (byte) 0xD8, (byte) 0xFF,
                    (byte) 0xE0, 0x00, 0x10,
                    0x4A, 0x46, 0x49, 0x46,
                    0x00, 0x01,
                    (byte) 0xFF, (byte) 0xD9
            };

            case "TEXT" -> "Unsupported text"
                    .getBytes(StandardCharsets.UTF_8);

            case "PNG" -> new byte[]{
                    (byte) 0x89, 0x50, 0x4E, 0x47,
                    0x0D, 0x0A, 0x1A, 0x0A
            };

            default -> throw new IllegalArgumentException(
                    "Unknown fixture type: " + type
            );
        };
    }
}