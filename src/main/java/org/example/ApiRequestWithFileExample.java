package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ApiRequestWithFileExample {
    public static void main(String[] args) {
        try {

            Path filePath = Paths.get("resources/index.html");
            String fileContent = Files.readString(filePath);


            String boundary = UUID.randomUUID().toString();
            String CRLF = "\r\n"; // Line separator required by multipart/form-data.


            StringBuilder requestBody = new StringBuilder();
            requestBody.append("--").append(boundary).append(CRLF);
            requestBody.append("Content-Disposition: form-data; name=\"file\"; filename=\"index.html\"").append(CRLF);
            requestBody.append("Content-Type: text/html").append(CRLF);
            requestBody.append(CRLF);
            requestBody.append(fileContent).append(CRLF);
            requestBody.append("--").append(boundary).append("--").append(CRLF);


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.example.com/upload"))
                    .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
