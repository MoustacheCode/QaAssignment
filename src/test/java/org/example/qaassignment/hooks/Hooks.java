package org.example.qaassignment.hooks;

import org.example.qaassignment.support.ScenarioContext;
import io.cucumber.java.Before;

public class Hooks {

    private final ScenarioContext context;

    public Hooks(ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void resetContext() {
        context.reset();
    }
}