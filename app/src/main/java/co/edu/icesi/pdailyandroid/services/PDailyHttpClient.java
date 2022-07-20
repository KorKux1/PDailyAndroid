package co.edu.icesi.pdailyandroid.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PDailyHttpClient {

    public static String doPostRequest(String path, String body) {
        return doPostRequest(path, body, null);
    }

    public static String doPostRequest(String path, String body, String authToken) {
        try {
            URL url = new URL(path);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            if (authToken != null) {
                connection.setRequestProperty("Authorization", "Bearer " + authToken);
            }

            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            writer.write(body);
            writer.flush();
            out.close();
            writer.close();

            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            in.close();
            reader.close();

            connection.disconnect();
            return responseBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
