package utility;

import okhttp3.*;
import com.google.gson.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class OpenAISuggester {

    private static final String OPENAI_API_KEY;

    static {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/test/resources/config/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
        OPENAI_API_KEY = props.getProperty("openai.api.key");
    }

    public static void suggestGherkinScenarios(String apiResponseJson) {
        String prompt = "Act as you are a QA engineer. Based on this API JSON response, suggest 3-5 new test cases in Gherkin format (Given/When/Then) to test edge cases or invalid inputs:\n\n"
                + apiResponseJson;

        OkHttpClient client = new OkHttpClient();

        JsonObject json = new JsonObject();
        json.addProperty("model", "gpt-3.5-turbo");
        JsonArray messages = new JsonArray();

        JsonObject userMsg = new JsonObject();
        userMsg.addProperty("role", "user");
        userMsg.addProperty("content", prompt);

        messages.add(userMsg);
        json.add("messages", messages);

        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
        	if (response.code() == 429) {
                System.err.println("OpenAI rate limit hit (HTTP 429). Skipping AI suggestion for now.");
                return;
        	}
        	
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String result = response.body().string();
            JsonObject responseJson = JsonParser.parseString(result).getAsJsonObject();
            String suggestion = responseJson
                    .getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content").getAsString();

            System.out.println("Suggested Gherkin Test Scenarios:\n");
            System.out.println(suggestion);
            
            }


         catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

