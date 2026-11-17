package org.example.qaassignment.runners;


import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import org.springframework.context.annotation.Configuration;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/resources/features")
@ConfigurationParameters({
        @ConfigurationParameter(key= Constants.GLUE_PROPERTY_NAME, value="org.example.qaassignment"),
        @ConfigurationParameter(key= "cucumber.snippet-type", value ="camelcase"),
        @ConfigurationParameter(key= PLUGIN_PROPERTY_NAME, value= "pretty")
})

// Added for individual feature tests using tags
//@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@debug")

public class RunCucumberTest {

}