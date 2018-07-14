/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dcrs.utils;

import java.util.Calendar;

/**
 * Class used for logging. It has two levels : debug and error. If an error
 * message is logged, it is displayed both in the terminal and in a message
 * box.
 * @author tcstrain
 */
public class Logger {
    
    public static void log(String msg) {
        log(msg, false);
    }
    public static void log(String msg, boolean error) {
        int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int m = Calendar.getInstance().get(Calendar.MINUTE);
        int s = Calendar.getInstance().get(Calendar.SECOND);
        int ms = (int)(Calendar.getInstance().getTimeInMillis() % 1000);
        String milliseconds = ""+(s>99?s:(s>9?"0"+s:"00"+s));
        String time = "  ["+(h>9?h:"0"+h)+":"+(m>9?m:"0"+m)+":"+milliseconds+"."+ms+"] ";
        if (error) {
            System.out.flush();
            System.err.println(time+msg);
            System.err.flush();
        } else {
            System.err.flush();
            System.out.println(time+msg);
            System.out.flush();
        }
    }
}
