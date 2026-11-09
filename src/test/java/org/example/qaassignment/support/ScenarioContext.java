package org.example.qaassignment.support;

import io.cucumber.java.Before;
import io.restassured.response.Response;

public class ScenarioContext {
    private Response response;

    public Response response(){
        if (response == null){
            throw new IllegalStateException("No API response available");
        }
        return response;
    }

    public void setResponse(Response response){
        this.response = response;
    }


    // Reset after each scenario
    @Before
    public void reset(){
        response = null;
    }
}