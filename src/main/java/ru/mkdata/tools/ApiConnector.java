package ru.mkdata.tools;

import java.io.IOException;

public class ApiConnector {

    public static boolean setIP(){
        try {
            String state = Request.sendPUT("https://api.cloudflare.com/client/v4/zones/911c894f957a627325c57da6fb21a317/dns_records/a2aa32a2656df55a236b3e38c9c5cdff",
                    "{\"type\":\"A\",\"name\":\"direct.mkdata.ru\",\"content\":\"" + getExtIP() + "\",\"ttl\":1,\"proxied\":false}");
            if (state.contains("success")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String getExtIP(){
        try {
            String ip = Request.sendGet("http://icanhazip.com");
            return ip;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
