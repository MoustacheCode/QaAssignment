package org.example.qaassignment.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EnvironmentManager {

    @Value("${api.base.url}")
    private String apiBaseUrl;
}
