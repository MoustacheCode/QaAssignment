package org.example.qaassignment.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    public String getInputFolder() {
        return inputFolder;
    }

    public String getProcessFolder() {
        return processFolder;
    }

    public String getEncodedFolder() {
        return encodedFolder;
    }

    public String getErrorFolder() {
        return errorFolder;
    }
}
