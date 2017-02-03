package com.company.tools;

/**
 * Created by user on 31.01.2017.
 */
public class TimeConverter{
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    /**
     * converts ms to comfortable time view
     * @param ms milliseconds
     * @return time in string
     */
    public static String convertMs(long ms){

        StringBuffer text = new StringBuffer("");
        if (ms > DAY){
            text.append(ms / DAY).append("d ");
            ms %= DAY;
        }
        if (ms > HOUR){
            text.append(ms / HOUR).append("h ");
            ms %= HOUR;
        }
        if (ms > MINUTE){
            text.append(ms / MINUTE).append("m ");
            ms %= MINUTE;
        }
        if (ms > SECOND){
            text.append(ms / SECOND).append("s ");
            ms %= SECOND;
        }

        text.append(ms + "ms");

        return text.toString();
    }


}
