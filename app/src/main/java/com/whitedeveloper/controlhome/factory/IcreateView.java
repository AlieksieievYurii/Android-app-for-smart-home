package com.whitedeveloper.controlhome.factory;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import com.whitedeveloper.custom.textview.ControllerTextView;

public interface IcreateView {
    void createButton(ControllerButton controllerButton);
    void createSeekBar(ControllerSeekBar controllerSeekBar);
    void createTextView(ControllerTextView controllerTextView);
    void finishCreating();
}
