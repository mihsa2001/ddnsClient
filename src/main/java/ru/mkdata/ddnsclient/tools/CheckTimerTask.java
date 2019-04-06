package ru.mkdata.ddnsclient.tools;

import org.pmw.tinylog.Logger;

import java.util.TimerTask;

/**
 * @author Mikhail Klimov
 */

public class CheckTimerTask extends TimerTask {

    public void run() {
        ApiConnector.getZoneId();
        ApiConnector.getDnsId();
        if (!ApiConnector.getDetails().contains(ApiConnector.getExtIP())){
            ApiConnector.setIP();
        } else Logger.info("not needed");
    }
}
