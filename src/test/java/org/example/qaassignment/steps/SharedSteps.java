package org.example.qaassignment.steps;

import io.cucumber.java.en.Then;
import org.example.qaassignment.config.EnvironmentManager;
import org.example.qaassignment.support.ScenarioContext;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class SharedSteps {

    private final EnvironmentManager environmentManager;
    private final ScenarioContext context;

    public SharedSteps(EnvironmentManager environmentManager, ScenarioContext context) {
        this.environmentManager = environmentManager;
        this.context = context;
    }


    @Then("the API response code is {int}")
    public void theAPIResponseCodeIs(Integer expectedStatusCode) {
        // Retrieve the response saved by the When step
        // and check that the actual HTTP status matches the expected
        context.response()
                .then()
                .statusCode(expectedStatusCode);
    }

    @Then("the response status is {string}")
    public void theResponseStatusIs(String expectedStatus) {
        // Read the 'Status' value from the JSON response body
        String actualStatus = context.response()
                .jsonPath()
                .getString("status");

        // Check the status returned by the API matches
        // the status expected in the Scenario
        assertEquals(expectedStatus, actualStatus);
    }

    @Then("the response message is {string}")
    public void theResponseMessageIs(String expectedMessage) {
        // Read the message field from the API response
        String actualMessage = context.response()
                .jsonPath()
                .getString("message");

        // Check that the API returned the expected message
        assertEquals(expectedMessage, actualMessage);
    }

    @Then("the response unsupported file name is {string}")
    public void theResponseUnsupportedFileNameIs(
            String expectedFileName
    ) {
            // Read the unsupported filename from the API response
        String actualFileName = context.response()
                .jsonPath()
                .getString("unsupportedFileName");

        // Check that the API identified the expected unsupported file
        assertEquals(expectedFileName, actualFileName);
    }

    @Then("{string} exists in the error folder")
    public void existsInTheErrorFolder(String zipFileName) {

        // Build the expected location of the rejected ZIP
        Path zipPath = environmentManager.errorFolder().resolve(zipFileName);

        // Check that the application moved the ZIP into the error folder
        assertTrue(Files.exists(zipPath),
                "Expected ZIP to exist in error folder: " + zipPath);
    }

    @Then("{string} no longer exists in the input folder")
    public void noLongerExistsInTheInputFolder(String zipFileName) {

        // Build the original location of the ZIP in input folder
        Path zipPath = environmentManager.inputFolder().resolve(zipFileName);

        // Check that processing removed the ZP from the folder
        assertFalse(Files.exists(zipPath),
                "Expected ZIP to be removed from input Folder: " + zipPath
        );
    }

    @Then("{string} does not exist in the error folder")
    public void doesNotExistInTheErrorFolder(String zipFileName) {

        // Build the location where the ZIP would appear if processing failed.
        Path zipPath = environmentManager.errorFolder().resolve(zipFileName);

        // Successful processing should not place Zp in the error folder
        assertFalse(Files.exists(zipPath),
                "ZIP should not exist in the error folder: " + zipPath);
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