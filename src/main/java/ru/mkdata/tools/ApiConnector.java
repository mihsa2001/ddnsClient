package ru.mkdata.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.pmw.tinylog.Logger;
import ru.mkdata.entity.DnsID.DnsRoot;
import ru.mkdata.entity.ZoneID.ZoneRoot;

import java.io.IOException;

public class ApiConnector {

    private static String zoneId;
    private static String dnsId;
    private static String domain;
    private static String subDomain;

    public static void setDomain(String domain) {
        ApiConnector.domain = domain;
    }

    public static void setSubDomain(String subDomain) {
        ApiConnector.subDomain = subDomain;
    }

    public static void getZoneId() {
        try {
            String data = Request.sendApiGet("https://api.cloudflare.com/client/v4/zones");
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            ZoneRoot root = gson.fromJson(data, ZoneRoot.class);
            for (int i = 0; i < root.getResult().size(); i++) {
                if (root.getResult().get(i).getName().equals(domain)) {
                    zoneId = root.getResult().get(i).getId();
                }
            }
        } catch (IOException e) {
            Logger.error("Cannot get zone id");
            e.printStackTrace();
        }
    }

    public static void getDnsId() {
        try {
            String data = Request.sendApiGet("https://api.cloudflare.com/client/v4/zones/" + zoneId + "/dns_records");
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            DnsRoot root = gson.fromJson(data, DnsRoot.class);
            for (int i = 0; i < root.getResult().size(); i++) {
                if (root.getResult().get(i).getName().equals(subDomain)) {
                    dnsId = root.getResult().get(i).getId();
                }
            }
        } catch (IOException e) {
            Logger.error("Cannot get DNS record id");
            e.printStackTrace();
        }
    }

    public static String getDetails(){
        try {
            String info = Request.sendApiGet("https://api.cloudflare.com/client/v4/zones/" + zoneId + "/dns_records/" + dnsId);
            return info;
        } catch (IOException | NullPointerException e) {
            Logger.error("Cloudflare Server is offline");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean setIP(){
        try {
            String state = Request.sendPUT("https://api.cloudflare.com/client/v4/zones/" + zoneId + "/dns_records/" + dnsId,
                    "{\"type\":\"A\",\"name\":\"" + subDomain + "\",\"content\":\"" + getExtIP() + "\",\"ttl\":1,\"proxied\":false}");
            if (state.contains("success")) {
                Logger.info("success" + "" + getExtIP());
                return true;
            }
            Logger.error(state);
        } catch (IOException | NullPointerException e) {
            Logger.error("Cloudflare Server is offline");
            e.printStackTrace();
        }
        return false;
    }

    public static String getExtIP(){
        try {
            String ip = Request.sendGet("http://icanhazip.com");
            return ip;
        } catch (IOException | NullPointerException e) {
            //TODO проверка по другому сервису
            Logger.error("Icanhazip Server is offline");
            e.printStackTrace();
        }
        return null;
    }
}
