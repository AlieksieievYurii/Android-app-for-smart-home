package com.whitedeveloper.controlhome.model.cheduletimer;
import java.util.Timer;

public class ScheduleTimeUpDate
{
    private static final int PERIOD = 1000;
    private static final int START_DELAY = 0;
    private final ItimeUpDate itimeUpDate;
    private Timer timer;
    private boolean isRun;

    public boolean isRun() {
        return isRun;
    }

    public ScheduleTimeUpDate(ItimeUpDate itimeUpDate) {
        this.itimeUpDate = itimeUpDate;
        isRun = false;
    }

    public void run()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(),START_DELAY,PERIOD);
        isRun = true;
    }

    public void stop()
    {
        timer.cancel();
        timer.purge();
        timer = null;
        isRun = false;
    }

    private class TimerTask extends java.util.TimerTask
    {
        @Override
        public void run() {
            itimeUpDate.timeUpDate();
        }
    }

}
