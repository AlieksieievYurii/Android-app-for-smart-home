package com.whitedeveloper.controlhome.controller;

import android.content.Context;
import android.os.Build;
import android.os.Vibrator;

class VibrationButton
{
    private static final long TIME_VIBRATION = 100;

    private Context context;
    private Vibrator vibrator;
    private boolean activated = false;

    VibrationButton(Context context) {
            this.context = context;
            this.vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    boolean isActivated() {
        return activated;
    }

    void setActivated(boolean activated) {
        this.activated = activated;
    }



    void pressEffect()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (activated)
                vibrator.vibrate(TIME_VIBRATION);
        }
    }
}
