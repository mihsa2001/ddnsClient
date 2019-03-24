package ru.mkdata;

import ru.mkdata.tools.CheckTimerTask;

import java.util.Timer;

public class Main {

    public static void main(String[] args){
        Timer checkIpTimer = new Timer();
        CheckTimerTask checkTimerTask = new CheckTimerTask();
        //TODO параметр частоты повтора запроса
        checkIpTimer.schedule(checkTimerTask, 1000, 5000);
    }
}
