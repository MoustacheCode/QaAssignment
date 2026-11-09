package org.example.qaassignment.clients;

import io.restassured.response.Response;
import org.example.qaassignment.config.EnvironmentManager;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

// Tell Spring to create and manage this API client.
@Component
public class ProcessingClient {

    // Gives the client access to API configuration,
    // I.e base URL and processing endpoint.
    private final EnvironmentManager environmentManager;

    // Spring injects the EnvironmentManager when it creates ProcessingClient.
    public ProcessingClient(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    // Call the API endpoint that tells the application
    // to find and process the ZIP in its input folder.
    public Response process() {

        // Build and send the API request using REST Assured.
        return given()

                // Set the main application address.
                // For example: http://localhost:8080
                .baseUri(environmentManager.getBaseUrl())

                // Request configuration is complete.
                // We are now ready to send the HTTP request.
                .when()

                // Send a GET request to the configured processing endpoint.
                // Combined example:
                // GET http://localhost:8080/api/files/find
                .get(environmentManager.getProcessPath());
    }
}