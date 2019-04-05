package ru.mkdata.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {
    public static String Api_Key;
    public static String Email;

    public static String sendApiGet(final String url) throws IOException {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Auth-Key", Api_Key);
        connection.setRequestProperty("X-Auth-Email", Email);
        connection.setRequestProperty("Content-Type", "application/json");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static String sendGet(final String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static String sendPUT(final String url, final String data) throws IOException{
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("X-Auth-Key",Api_Key);
        connection.setRequestProperty("X-Auth-Email",Email);
        connection.setRequestProperty("Content-Type","application/json");
        OutputStreamWriter out = new OutputStreamWriter(
                connection.getOutputStream());
        out.write(data);
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static void setApi_Key(String api_Key) {
        Api_Key = api_Key;
    }

    public static void setEmail(String email) {
        Email = email;
    }
}
