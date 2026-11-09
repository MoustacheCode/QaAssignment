package org.example.qaassignment.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Component
public class EnvironmentManager {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${api.process.path}")
    private String processPath;

    @Value("${folders.input}")
    private String inputFolder;

    @Value("${folders.process}")
    private String processFolder;

    @Value("${folder.encoded}")
    private String encodedFolder;

    @Value("${folders.error}")
    private String errorFolder;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getProcessPath() {
        return processPath;
    }

    public Path getInputFolder() {
        return Path.of(inputFolder);
    }

    public Path getProcessFolder() {
        return Path.of(processFolder);
    }

    public Path getEncodedFolder() {
        return Path.of(encodedFolder);
    }

    public Path getErrorFolder() {
        return Path.of(errorFolder);
    }
}
