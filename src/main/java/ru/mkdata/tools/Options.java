package ru.mkdata.tools;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

public class Options extends OptionsBase {
    @Option(
            name = "period",
            abbrev = 'p',
            category = "startup",
            defaultValue = "60"
    )
    public int period;
}
