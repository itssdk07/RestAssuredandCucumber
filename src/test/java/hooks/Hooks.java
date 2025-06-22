package hooks;

import stepDefinitions.Context;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import utility.OpenAISuggester;

public class Hooks {
    @Before
    public void setup() {
        System.out.println("Starting API test scenario...");
    }
    
    
    @After
    public void afterScenario() {
        if (Context.latestApiResponse != null) {
            String json = Context.latestApiResponse.getBody().asPrettyString();
            OpenAISuggester.suggestGherkinScenarios(json);
        } else {
            System.out.println("No API response available for AI suggestion.");
        }
    }
}