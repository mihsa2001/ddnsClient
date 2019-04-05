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

    @Option(
            name = "api_key",
            abbrev = 'k',
            category = "startup",
            defaultValue = ""
    )
    public String key;

    @Option(
            name = "api_email",
            abbrev = 'e',
            category = "startup",
            defaultValue = ""
    )
    public String email;

    @Option(
            name = "Domain",
            abbrev = 'd',
            category = "startup",
            defaultValue = ""
    )
    public String domain;

    @Option(
            name = "Subdomain",
            abbrev = 's',
            category = "startup",
            defaultValue = ""
    )
    public String subDomain;
}
