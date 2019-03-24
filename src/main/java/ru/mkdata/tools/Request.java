package ru.mkdata.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {
    public static String Api_Key = "cb4decb166961a75f3296c950c8d2d9a771c5";
    public static String Email = "mihsa2001@gmail.com";

    public static String sendGet(final String url,boolean useHeaders) throws IOException {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            if (useHeaders) {
                connection.setRequestProperty("X-Auth-Key", Api_Key);
                connection.setRequestProperty("X-Auth-Email", Email);
                connection.setRequestProperty("Content-Type", "application/json");
            }
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

}
