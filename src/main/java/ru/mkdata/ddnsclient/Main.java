package ru.mkdata.ddnsclient;

import com.google.devtools.common.options.OptionsParser;
import ru.mkdata.ddnsclient.tools.ApiConnector;
import ru.mkdata.ddnsclient.tools.CheckTimerTask;
import ru.mkdata.ddnsclient.tools.Options;
import ru.mkdata.ddnsclient.tools.Request;

import java.util.Timer;

/**
 * @author Mikhail Klimov
 */

public class Main {

    public static void main(String[] args){
        setLoginProperties(optionsParsing(args));
        setPeriodTimer(optionsParsing(args));
        ApiConnector.setDomain(optionsParsing(args).domain);
        ApiConnector.setSubDomain(optionsParsing(args).subDomain);
    }

    public static void setPeriodTimer(Options options) {
        CheckTimerTask checkTimerTask = new CheckTimerTask();
        Timer checkIpTimer = new Timer();
        checkIpTimer.schedule(checkTimerTask, 200, options.period * 60000);
    }

    public static void setLoginProperties(Options options) {
        Request.setEmail(options.email);
        Request.setApi_Key(options.key);
    }

    public static Options optionsParsing(String[] args) {
        OptionsParser parser = OptionsParser.newOptionsParser(Options.class);
        parser.parseAndExitUponError(args);
        Options options = parser.getOptions(Options.class);
        return options;
    }
}
