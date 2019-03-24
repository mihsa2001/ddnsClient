package ru.mkdata.tools;

import org.pmw.tinylog.Logger;

import java.io.IOException;

public class ApiConnector {

    public static String getDetails(){
        try {
            String info = Request.sendGet("https://api.cloudflare.com/client/v4/zones/911c894f957a627325c57da6fb21a317/dns_records/a2aa32a2656df55a236b3e38c9c5cdff",true);
            return info;
        } catch (IOException e) {
            Logger.error(e.toString());
            e.printStackTrace();
        }
        return null;
    }
    public static boolean setIP(){
        try {
            String state = Request.sendPUT("https://api.cloudflare.com/client/v4/zones/911c894f957a627325c57da6fb21a317/dns_records/a2aa32a2656df55a236b3e38c9c5cdff",
                    "{\"type\":\"A\",\"name\":\"direct.mkdata.ru\",\"content\":\"" + getExtIP() + "\",\"ttl\":1,\"proxied\":false}");
            if (state.contains("success")) return true;
            Logger.error(state);
        } catch (IOException e) {
            Logger.error(e.toString());
            e.printStackTrace();
        }
        return false;
    }
    public static String getExtIP(){
        try {
            String ip = Request.sendGet("http://icanhazip.com",false);
            return ip;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
