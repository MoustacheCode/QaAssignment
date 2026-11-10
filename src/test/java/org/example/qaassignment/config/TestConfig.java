package org.example.qaassignment.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.qaassignment.QaAssignmentApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = QaAssignmentApplication.class)
@ActiveProfiles("local")
public class TestConfig {

}
