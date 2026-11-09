package org.example.qaassignment.steps;

import io.cucumber.java.en.Then;
import org.example.qaassignment.config.EnvironmentManager;
import org.example.qaassignment.support.ScenarioContext;

import java.io.IOException;
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

    @Then("the response source file is {string}")
    public void theResponseSourceFileIs(String expectedSourceFile) {

        // Read the original ZIP filename returned in the API response.
        String actualSourceFile = context.response()
                .jsonPath()
                .getString("sourceFile");

        // Check that the API reports the expected source ZIP.
        assertEquals(expectedSourceFile, actualSourceFile);
    }

    @Then("the response processed file is {string}")
    public void theResponseProcessedFileIs(String expectedProcessedFile) {

        // Read the processed ZIP filename returned in the API response.
        String actualProcessedFile = context.response()
                .jsonPath()
                .getString("processedFile");

        // Check that the API returned the expected processed filename.
        assertEquals(expectedProcessedFile, actualProcessedFile);
    }

    @Then("the response encoded file is {string}")
    public void theResponseEncodedFileIs(String expectedEncodedFile) {

        // Read the generated Base64 filename returned in the API response.
        String actualEncodedFile = context.response()
                .jsonPath()
                .getString("encodedFile");

        // Check that the API returned the expected encoded filename.
        assertEquals(expectedEncodedFile, actualEncodedFile);
    }

    @Then("{string} exists in the processed folder")
    public void existsInTheProcessedFolder(String zipFileName) {

        // Build the expected location of the original ZIP after processing.
        Path zipPath = environmentManager.processFolder()
                .resolve(zipFileName);

        // Check that the application moved the original ZIP
        // from input into the processed folder.
        assertTrue(
                Files.exists(zipPath),
                "Expected original ZIP to exist in processed folder: " + zipPath
        );
    }

    @Then("the response processed file exists in the processed folder")
    public void theResponseProcessedFileExistsInTheProcessedFolder() {

        // Get the processed filename reported by the API.
        String processedFileName = context.response()
                .jsonPath()
                .getString("processedFile");

        // Build the physical location where that file should exist.
        Path processedFilePath = environmentManager.processFolder()
                .resolve(processedFileName);

        // Confirm that the file reported by the API really exists.
        assertTrue(
                Files.exists(processedFilePath),
                "Expected processed ZIP to exist: " + processedFilePath
        );
    }

    @Then("the response encoded file exists in the encoded folder")
    public void theResponseEncodedFileExistsInTheEncodedFolder() {

        // Get the encoded filename reported by the API.
        String encodedFileName = context.response()
                .jsonPath()
                .getString("encodedFile");

        // Build the physical location of the expected Base64 output.
        Path encodedFilePath = environmentManager.encodedFolder()
                .resolve(encodedFileName);

        // Confirm that the encoded output reported by the API actually exists.
        assertTrue(
                Files.exists(encodedFilePath),
                "Expected encoded output to exist: " + encodedFilePath
        );
    }

    @Then("{string} remains in the input folder")
    public void remainsInTheInputFolder(String zipFileName) {

        // Build the expected path of the ZIP in the input folder.
        Path zipPath = environmentManager.inputFolder()
                .resolve(zipFileName);

        // EmptyZipException is currently thrown before the ZIP is moved,
        // so verify that the original ZIP remains in input.
        assertTrue(
                Files.exists(zipPath),
                "Expected ZIP to remain in input folder: " + zipPath
        );
    }

    @Then("no processed ZIP has been created")
    public void noProcessedZIPHasBeenCreated() throws IOException {

        // Rejected input should not produce anything in the processed folder.
        try (var files = Files.list(environmentManager.processFolder())) {

            assertTrue(
                    files.findAny().isEmpty(),
                    "Expected no processed ZIP to be created"
            );
        }
    }


    @Then("no Base64 output has been created")
    public void noBase64OutputHasBeenCreated() throws IOException {

        // Rejected input should not generate anything in the encoded folder.
        try (var files = Files.list(environmentManager.encodedFolder())) {

            assertTrue(
                    files.findAny().isEmpty(),
                    "Expected no encoded/Base64 output to be created"
            );
        }
    }
}