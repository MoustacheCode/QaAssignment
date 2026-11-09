package org.example.qaassignment.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.qaassignment.config.EnvironmentManager;
import org.example.qaassignment.support.ScenarioContext;

import java.io.IOException;
import java.nio.file.Files;

import static org.example.qaassignment.utils.FileUtils.emptyFolder;

public class ZipExtractSteps {

    private final EnvironmentManager environmentManager;
    private final ScenarioContext context;

    public ZipExtractSteps(EnvironmentManager environmentManager, ScenarioContext context) {
        this.environmentManager = environmentManager;
        this.context = context;
    }

    @Given("the ZIP processing folders exist")
    public void theZIPProcessingFoldersExist() throws IOException {
        Files.createDirectories(environmentManager.inputFolder());
        Files.createDirectories(environmentManager.processFolder());
        Files.createDirectories(environmentManager.encodedFolder());
        Files.createDirectories(environmentManager.errorFolder());
    }


    @Given("the ZIP processing folders are empty")
    public void theZIPProcessingFoldersAreEmpty() throws IOException {
        emptyFolder(environmentManager.inputFolder());
        emptyFolder(environmentManager.processFolder());
        emptyFolder(environmentManager.encodedFolder());
        emptyFolder(environmentManager.errorFolder());
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