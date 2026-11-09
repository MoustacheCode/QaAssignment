package org.example.qaassignment.support;

import io.cucumber.java.Before;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;

import java.nio.file.Path;

@ScenarioScope
public class ScenarioContext {
    private Response response;
    private Path zipFile;

    public Response response(){
        if (response == null){
            throw new IllegalStateException("No API response available");
        }
        return response;
    }

    public void setResponse(Response response){
        this.response = response;
    }

    public Path getZipFile() {
        return zipFile;
    }

    public void setZipFile(Path zipFile) {
        this.zipFile = zipFile;
    }




    // Reset after each scenario
    @Before
    public void reset(){
        response = null;
        zipFile = null;
    }
}