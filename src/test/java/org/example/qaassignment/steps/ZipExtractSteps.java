package org.example.qaassignment.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.qaassignment.clients.ProcessingClient;
import org.example.qaassignment.config.EnvironmentManager;
import org.example.qaassignment.support.ScenarioContext;
import org.example.qaassignment.support.TestFileFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.example.qaassignment.utils.FileUtils.emptyFolder;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipExtractSteps {

    private final EnvironmentManager environmentManager;
    private final ScenarioContext context;
    private final ProcessingClient processingClient;

    public ZipExtractSteps(EnvironmentManager environmentManager, ScenarioContext context, ProcessingClient processingClient) {
        this.environmentManager = environmentManager;
        this.context = context;
        this.processingClient = processingClient;
    }

    @Given("the ZIP processing folders exist")
    public void theZIPProcessingFoldersExist() throws IOException {
        // Make sure all folders required for ZIP processing exist before the scenario starts
        assertTrue(Files.exists(environmentManager.inputFolder()), "The Input folder doesn't exist");
        assertTrue(Files.exists(environmentManager.processFolder()), "The Process folder doesn't exist");
        assertTrue(Files.exists(environmentManager.encodedFolder()), "The Encroded folder doesn't exist");
        assertTrue(Files.exists(environmentManager.errorFolder()), "The Error folder doesn't exist");
    }


    @Given("the ZIP processing folders are empty")
    public void theZIPProcessingFoldersAreEmpty() throws IOException {
        // Remove files left by previous scenarios so each test starts from a clean ZIP
        emptyFolder(environmentManager.inputFolder());
        emptyFolder(environmentManager.processFolder());
        emptyFolder(environmentManager.encodedFolder());
        emptyFolder(environmentManager.errorFolder());
    }


    @Given("a ZIP file named {string} contains:")
    public void aZIPFileNamedContains(
            String zipFileName,
            DataTable dataTable
    ) throws IOException {
        // Take each row from the Cucumber DataTable
        // and convert it into a Java Map containing fileName and type
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        // Convert each row into an ArchiveEntry that the
        // TestFileFactory understands.
        List<TestFileFactory.ArchiveEntry> entries = rows.stream().map(row -> new TestFileFactory.ArchiveEntry(row.get("fileName"),
                row.get("type"))).toList();

        // Create the actual ZIP file inside the configured input folder
        // The ZIP name comes from the scenario and the entries come
        // from the DataTable above
        Path zipPath = TestFileFactory.createZip(
                environmentManager.inputFolder(),
                zipFileName,
                entries
        );

        // Remember the created ZIP for the rest of this scenario
        context.setZipFile(zipPath);
    }

    @Given("an empty ZIP file named {string} is created")
    public void anEmptyZIPFileNamedIsCreated(String zipFileName) throws IOException {

        // Create an empty ZIP insider the configured input folder
        Path zipPath = TestFileFactory.createEmptyZip(
                environmentManager.inputFolder(),
                zipFileName
        );

        // Remember the ZIP path so the 'When' step can process the same file
        context.setZipFile(zipPath);
    }

    @When("ZIP file processing is requested")
    public void zipFileProcessingIsRequested() {

        // Ask the API to find and process the ZIP file
        // that has already been created in the input folder.
        Response response = processingClient.process();

        // Save the response so the Then steps can make assertions against it.
        context.setResponse(response);
    }



//    @Then("{string} remains in the input folder")
//    public void remainsInTheInputFolder(String zipFileName) {
//
//        // Build the expected location of the ZIP in the input folder.
//        Path zipPath = environmentManager.inputFolder()
//                .resolve(zipFileName);
//
//        // An empty ZIP is not moved by the current service implementation.
//        assertTrue(
//                Files.exists(zipPath),
//                "Expected ZIP to remain in input folder: " + zipPath
//        );
//    }
}