package ru.project.congratSystem.service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URL;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;


@Service
public class GptService {
    public String callPythonService(String question) {
        try {
            URL url = new URL("http://localhost:5000/gpt");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");


            String jsonInputString = "{\"question\": \"" + question + "\"}";

            try (OutputStream os = connection.getOutputStream()) {

                byte[] input = jsonInputString.getBytes();
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }


                    JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
                    String answer = jsonResponse.get("answer").getAsString();
                    return answer;
                }
            } else {
                return "Ошибка вызова Python-сервиса. HTTP-код: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Исключение: " + e.getMessage();
        }
    }
}
