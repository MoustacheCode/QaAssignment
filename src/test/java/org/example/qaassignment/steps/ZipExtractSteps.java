package org.example.qaassignment.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.qaassignment.config.EnvironmentManager;
import org.example.qaassignment.support.ScenarioContext;

public class ZipExtractSteps {

    private final EnvironmentManager environmentManager;
    private final ScenarioContext context;

    public ZipExtractSteps(EnvironmentManager environmentManager, ScenarioContext context) {
        this.environmentManager = environmentManager;
        this.context = context;
    }


    @Given("a ZIP file named {string} contains:")
    public void aZIPFileNamedContains(
            String zipFileName,
            DataTable dataTable
    ) {
        throw new io.cucumber.java.PendingException();
    }

    @Given("an empty ZIP file named {string} is created")
    public void anEmptyZIPFileNamedIsCreated(String zipFileName) {
        throw new io.cucumber.java.PendingException();
    }

    @When("the ZIP file {string} is submitted for processing")
    public void theZIPFileIsSubmittedForProcessing(String zipFileName) {
        throw new io.cucumber.java.PendingException();
    }
}