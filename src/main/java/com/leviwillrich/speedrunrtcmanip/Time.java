package com.leviwillrich.speedrunrtcmanip;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

public class Time {
    private static final SimpleDateFormat windowsDateFormatter =  new SimpleDateFormat("yy-MM-dd");
    private static final SimpleDateFormat windowsTimeFormatter =  new SimpleDateFormat("hh:mm:ss");


    private static final Map<OSUtils.OS, String> setNtpCommands = new EnumMap<>(OSUtils.OS.class);
    private static final Map<OSUtils.OS, String> clearNtpCommands = new EnumMap<>(OSUtils.OS.class);

    static {
        setNtpCommands.put(OSUtils.OS.WINDOWS, "net.exe start w32time");
        setNtpCommands.put(OSUtils.OS.UNIX, "timedatectl set-ntp 1");
        clearNtpCommands.put(OSUtils.OS.WINDOWS, "net.exe stop w32time");
        clearNtpCommands.put(OSUtils.OS.UNIX, "timedatectl set-ntp 0");
    }



    public static void turnOffNtp() throws IOException {
        Runtime.getRuntime().exec(clearNtpCommands.get(OSUtils.os));
    }

    public static void turnOnNtp() throws IOException {
        Runtime.getRuntime().exec(setNtpCommands.get(OSUtils.os));
    }

    public static void setDate(Date date) throws IOException {
        switch (OSUtils.os) {
            case WINDOWS -> {
                Runtime.getRuntime().exec("cmd /C date " + windowsDateFormatter.format(date));
                Runtime.getRuntime().exec("cmd /C time " + windowsTimeFormatter.format(date));
            }
            case UNIX -> {
                Runtime.getRuntime().exec("date -s @"+date.getTime());
            }
            case UNSUPPORTED -> {
                // this will never happen

            }
        }
    }
}
