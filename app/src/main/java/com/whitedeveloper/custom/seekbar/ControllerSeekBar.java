package com.whitedeveloper.custom.seekbar;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.view.View;
import android.widget.LinearLayout;
import com.whitedeveloper.controlhome.factory.seekbar.CreatorSeekBar;
import com.whitedeveloper.custom.PinArduino;


public class ControllerSeekBar {

    private final BoxedVertical seekBar;
    private final PinArduino arduinoPin;
    private final LinearLayout linearLayout;

    public ControllerSeekBar(BoxedVertical seekBar, LinearLayout linearLayout, PinArduino arduinoPin) {
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

    public PinArduino getPinArduino() {
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
