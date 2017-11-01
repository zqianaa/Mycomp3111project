package com.example.bot.spring;

/**
 * Created by qwmqza on 2017/10/28.
 */
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import java.util.TimerTask;
import java.util.Date;
import java.util.Calendar;
import java.util.Timer;
public class ReminderEngine {
    private Timer timer;
    private int hour;
    private int minutes;
    private int seconds;
    public ReminderEngine(int h, int m, int s, KitchenSinkController kc, String UserID) {
        hour = h;
        minutes = m;
        seconds = s;
        Date time = getTime();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                kc.reminder("test");
            }
        };
        timer = new Timer();
        try {
            timer.schedule(task, 10*1000, 60*1000);
        } catch (Exception e) {
            kc.reminder("error");
        }
        kc.reminder(time.toString());
    }
    public Date getTime() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day =c.get(Calendar.DAY_OF_MONTH);
        c.set(year, month, day, hour, minutes, seconds);
        Date time = c.getTime();
        return time;
    }
}