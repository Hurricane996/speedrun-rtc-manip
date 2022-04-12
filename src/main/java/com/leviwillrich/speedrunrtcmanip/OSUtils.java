package com.leviwillrich.speedrunrtcmanip;

import java.util.Locale;

public class OSUtils {
    public static String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);

    public static OS os =
            osName.contains("win") ? OS.WINDOWS :
                    osName.contains("nix") || osName.contains("aix") || osName.contains("nux") ? OS.UNIX :
                            OS.UNSUPPORTED;

    public enum OS {
        WINDOWS,
        UNIX,
        UNSUPPORTED
    }
}
