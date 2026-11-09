package org.example.qaassignment.steps;

import io.cucumber.java.en.Then;
import org.example.qaassignment.config.EnvironmentManager;
import org.example.qaassignment.support.ScenarioContext;

public class SharedSteps {

    private final EnvironmentManager environmentManager;
    private final ScenarioContext context;

    public SharedSteps(EnvironmentManager environmentManager, ScenarioContext context) {
        this.environmentManager = environmentManager;
        this.context = context;
    }


    @Then("the API response code is {int}")
    public void theAPIResponseCodeIs(Integer expectedStatusCode) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("the response status is {string}")
    public void theResponseStatusIs(String expectedStatus) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("the response message is {string}")
    public void theResponseMessageIs(String expectedMessage) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("the response unsupported file name is {string}")
    public void theResponseUnsupportedFileNameIs(
            String expectedFileName
    ) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("{string} exists in the error folder")
    public void existsInTheErrorFolder(String zipFileName) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("{string} no longer exists in the input folder")
    public void noLongerExistsInTheInputFolder(String zipFileName) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("{string} does not exist in the error folder")
    public void doesNotExistInTheErrorFolder(String zipFileName) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("a processed ZIP has been created")
    public void aProcessedZIPHasBeenCreated() {
        throw new io.cucumber.java.PendingException();
    }

    @Then("no processed ZIP has been created")
    public void noProcessedZIPHasBeenCreated() {
        throw new io.cucumber.java.PendingException();
    }

    @Then("Base64 output has been created")
    public void base64OutputHasBeenCreated() {
        throw new io.cucumber.java.PendingException();
    }

    @Then("no Base64 output has been created")
    public void noBase64OutputHasBeenCreated() {
        throw new io.cucumber.java.PendingException();
    }
}