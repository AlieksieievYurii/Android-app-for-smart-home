package com.whitedeveloper.custom.seekbar;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.view.View;
import android.widget.LinearLayout;
import com.whitedeveloper.custom.PinTCOD;


public class ControllerSeekBar {

    private final BoxedVertical seekBar;
    private final PinTCOD arduinoPin;
    private final LinearLayout linearLayout;

    public ControllerSeekBar(BoxedVertical seekBar, LinearLayout linearLayout, PinTCOD arduinoPin) {
        this.seekBar = seekBar;
        this.arduinoPin = arduinoPin;
        this.linearLayout = linearLayout;
        init();
    }


    private void init()
        {
            seekBar.setMax(255);
        }

    void setOnDoListener(BoxedVertical.OnValuesChangeListener onValuesChangeListener)
    {
        seekBar.setOnBoxedPointsChangeListener(onValuesChangeListener);
    }
    void setOnLongClickListener(View.OnLongClickListener onClickListener)
    {
        linearLayout.setOnLongClickListener(onClickListener);
    }

    public LinearLayout getLinearLayout() {
          return linearLayout;
      }

    public BoxedVertical getSeekBar() {
        return seekBar;
    }

    public PinTCOD getPinArduino() {
        return arduinoPin;
    }
    public int getId()
    {
        return seekBar.getId();
    }

    public void setValue(int value)
    {
        arduinoPin.setValue(value);
        seekBar.setValue(value);
    }
}
