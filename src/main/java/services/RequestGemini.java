package services;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

import java.io.IOException;

public class RequestGemini {
    private final String projectId = System.getenv("CLOUD_PROJECT_ID");
    private final String location = "us-central1";
    private final String modelName = "gemini-1.5-flash-001";

    public String request(String resume) throws IOException {
        String textPrompt =
                "Olá Gemini. Resuma em um texto conciso de até uma página do word o texto a seguir. Em seguida faça 3 perguntas discursivas relacionadas ao texto:\n\n'" + resume;

        return textInput(projectId, location, modelName, textPrompt);
    }

    private static String textInput(
            String projectId, String location, String modelName, String textPrompt) throws IOException {
        // Initialize client that will be used to send requests. This client only needs
        // to be created once, and can be reused for multiple requests.
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);

            GenerateContentResponse response = model.generateContent(textPrompt);
            return ResponseHandler.getText(response);
        }
    }

}
