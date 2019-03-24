package ru.mkdata.tools;

import java.io.IOException;
import java.util.TimerTask;

public class CheckTimerTask extends TimerTask {

    public void run() {
        boolean s = ApiConnector.setIP();
        System.out.println(s);
        System.exit(0);
    }
}
