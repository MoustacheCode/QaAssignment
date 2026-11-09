package org.example.qaassignment.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EnvironmentManager {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${test.input.path}")
    private String inputPath;

    @Value("${test.output.path}")
    private String outputPath;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }
}
