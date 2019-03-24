package ru.mkdata;

import com.google.devtools.common.options.OptionsParser;
import ru.mkdata.tools.CheckTimerTask;
import ru.mkdata.tools.Options;

import java.util.Timer;

public class Main {

    public static void main(String[] args){
        OptionsParser parser = OptionsParser.newOptionsParser(Options.class);
        parser.parseAndExitUponError(args);
        Options options = parser.getOptions(Options.class);
        Timer checkIpTimer = new Timer();
        CheckTimerTask checkTimerTask = new CheckTimerTask();
        checkIpTimer.schedule(checkTimerTask, 200, options.period * 60000);
    }
}
