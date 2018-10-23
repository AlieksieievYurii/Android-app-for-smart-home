package com.whitedeveloper.custom.seekbar;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;

import java.util.ArrayList;

public class ImplementationSeekBars implements BoxedVertical.OnValuesChangeListener
{
    private ArrayList<ControllerSeekBar> controllerSeekBars;
    private IonDoSeekBar ionDoSeekBar;

    public ImplementationSeekBars(ArrayList<ControllerSeekBar> controllerSeekBars, IonDoSeekBar ionDoSeekBar) {
        this.controllerSeekBars = controllerSeekBars;
        this.ionDoSeekBar = ionDoSeekBar;

        setSeekBarListener();
    }

    private void setSeekBarListener()
    {
        for(ControllerSeekBar controllerSeekBar : controllerSeekBars)
            controllerSeekBar.setOnDoListener(this);
    }

    @Override
    public void onPointsChanged(BoxedVertical boxedVertical, int i) {

    }

    @Override
    public void onStartTrackingTouch(BoxedVertical boxedVertical) {

    }

    @Override
    public void onStopTrackingTouch(BoxedVertical boxedVertical) {
        ControllerSeekBar controllerSeekBar = findBoxedVerticalByID(boxedVertical.getId());
        controllerSeekBar.setValue(boxedVertical.getValue());

        ionDoSeekBar.onDoSeekBar();
    }

    private ControllerSeekBar findBoxedVerticalByID(int id)
    {
        for(ControllerSeekBar controllerSeekBar : controllerSeekBars)
            if(controllerSeekBar.getId() == id)
                return controllerSeekBar;
        return null;
    }
}
