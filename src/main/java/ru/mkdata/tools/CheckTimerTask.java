package ru.mkdata.tools;

import org.pmw.tinylog.Logger;

import java.util.TimerTask;

public class CheckTimerTask extends TimerTask {

    public void run() {
        ApiConnector.getZoneId();
        ApiConnector.getDnsId();
        if (!ApiConnector.getDetails().contains(ApiConnector.getExtIP())){
            ApiConnector.setIP();
        } else Logger.info("not needed");
    }
}
