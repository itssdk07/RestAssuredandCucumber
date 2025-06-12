package hooks;

import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void setup() {
        System.out.println("Starting API test scenario...");
    }
}