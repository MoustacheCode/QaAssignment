package org.example.qaassignment.clients;

import io.restassured.response.Response;
import org.example.qaassignment.config.EnvironmentManager;
import org.springframework.stereotype.Component;

import java.util.Map;

import static io.restassured.RestAssured.given;

// Makes this class available for Spring to inject into our Step Definition class.
@Component
public class ProcessingClient {
    // Gives this client access to environment settings such as
    // the API base URL and processing endpoint
    private final EnvironmentManager environmentManager;

    // Spring provies the EnvironmentManager when it creates this client
    public ProcessingClient(EnvironmentManager environmentManager) {
        this.environmentManager = environmentManager;
    }

    // Sends a request to the API asking it to process the specified ZIP file
    // Response is returned so the Cucumber steps can check what the API sent back.
    public Response Process(String zipFileName) {

        // Build the JSON request body expected by ProcessingRequest
        Map<String, Object> requestBody = Map.of(
                "fileName", zipFileName
        );

        // REST Assured builds and sends the HTTP request
        return given().baseUri(environmentManager.getBaseUrl()) // Set the main address of application
                .contentType("application/json")  // Tell the API that the request body contains JSON
                .body(requestBody) // Add the request data to the HTTP request, REST Assured converts the Map into JSON
                .when() // Means it is now ready to Send
                .post(environmentManager.getProcessPath()); // Send POST request to the processing endpoint
    }
}
