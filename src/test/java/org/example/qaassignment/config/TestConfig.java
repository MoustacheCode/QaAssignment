package org.example.qaassignment.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.qaassignment.QaAssignmentApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@CucumberContextConfiguration
@SpringBootTest(classes = QaAssignmentApplication.class)
@ComponentScan(basePackages = { "org.example.qaassignment"})
public class TestConfig {

}
