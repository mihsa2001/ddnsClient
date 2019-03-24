package ru.mkdata.tools;

import java.io.IOException;
import java.util.TimerTask;

public class CheckTimerTask extends TimerTask {

    public void run() {
        if (!ApiConnector.getDetails().contains(ApiConnector.getExtIP())){
            System.out.println(ApiConnector.setIP());
        }else System.out.println(false);
    }
}
