package com.whitedeveloper.custom.seekbar;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.util.Log;
import android.view.View;
import com.whitedeveloper.custom.IonLongPressViewElement;

import java.util.ArrayList;

public class ImplementationSeekBars implements BoxedVertical.OnValuesChangeListener, View.OnLongClickListener
{
    private ArrayList<ControllerSeekBar> controllerSeekBars;
    private IonDoSeekBar ionDoSeekBar;
    private IonLongPressViewElement ionLongPressViewElement;

    public ImplementationSeekBars(ArrayList<ControllerSeekBar> controllerSeekBars, IonDoSeekBar ionDoSeekBar, IonLongPressViewElement ionLongPressViewElement) {
        this.controllerSeekBars = controllerSeekBars;
        this.ionDoSeekBar = ionDoSeekBar;
        this.ionLongPressViewElement = ionLongPressViewElement;

        setSeekBarListener();
    }

    private void setSeekBarListener()
    {
        for(ControllerSeekBar controllerSeekBar : controllerSeekBars)
        {
            controllerSeekBar.setOnDoListener(this);
            controllerSeekBar.setOnLongClickListener(this);
        }

    }

    @Override
    public void onPointsChanged(BoxedVertical boxedVertical, int i) {

    }

    @Override
    public void onStartTrackingTouch(BoxedVertical boxedVertical) {

    }

    @Override
    public void onStopTrackingTouch(BoxedVertical boxedVertical) {
        try {
            ControllerSeekBar controllerSeekBar = findBoxedVerticalByID(boxedVertical.getId());
            controllerSeekBar.setValue(boxedVertical.getValue());
            ionDoSeekBar.onDoSeekBar(boxedVertical.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ControllerSeekBar findBoxedVerticalByID(int id) throws Exception {
        for(ControllerSeekBar controllerSeekBar : controllerSeekBars)
            if(controllerSeekBar.getId() == id)
                return controllerSeekBar;
        throw new Exception("Not found SeekBar with ID " + String.valueOf(id));
    }

    @Override
    public boolean onLongClick(View view) {
        ionLongPressViewElement.longPress(view.getId());
        return true;
    }
}
