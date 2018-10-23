package com.whitedeveloper.custom.buttons;

import android.view.View;

import java.util.ArrayList;

public class ImplementationButtons implements View.OnClickListener
{
    private ArrayList<ControllerButton> controllerButtons;
    private IonClickButton ionClickButton;

    public ImplementationButtons(ArrayList<ControllerButton> controllerButtons,IonClickButton ionClickButton)
    {
        this.controllerButtons = controllerButtons;
        this.ionClickButton = ionClickButton;
        setOnClickListeners();
    }

    private void setOnClickListeners()
    {
        for(ControllerButton controllerButton : controllerButtons)
            controllerButton.setOnClickLIstener(this);
    }


    private ControllerButton getControllerById(long id)
    {
        for(ControllerButton controllerButton : controllerButtons)
            if(controllerButton.getId() == id)
                return controllerButton;
        return null;
    }

    @Override
    public void onClick(View view) {
        ControllerButton controllerButton = getControllerById(view.getId());
        controllerButton.buttonPressed();

        ionClickButton.onClickButton();
    }
}
