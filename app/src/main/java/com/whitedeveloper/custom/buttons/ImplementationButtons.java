package com.whitedeveloper.custom.buttons;

import android.view.View;
import com.whitedeveloper.custom.IonLongPressViewElement;
import com.whitedeveloper.custom.IrefreshActivity;

import java.util.ArrayList;

public class ImplementationButtons implements View.OnClickListener, View.OnLongClickListener
{
    private ArrayList<ControllerButton> controllerButtons;
    private IonClickButton ionClickButton;
    private IonLongPressViewElement ionLongPressViewElement;

    public ImplementationButtons(ArrayList<ControllerButton> controllerButtons,
                                 IonClickButton ionClickButton,
                                 IonLongPressViewElement ionLongPressViewElement
    )
    {
        this.controllerButtons = controllerButtons;
        this.ionClickButton = ionClickButton;
        this.ionLongPressViewElement = ionLongPressViewElement;
        setOnClickListeners();
    }

    private void setOnClickListeners()
    {
        for(ControllerButton controllerButton : controllerButtons) {
            controllerButton.setOnClickListener(this);
            controllerButton.setOnLongClickListener(this);
        }
    }


    private ControllerButton getControllerById(long id) throws Exception {
        for(ControllerButton controllerButton : controllerButtons)
            if(controllerButton.getId() == id)
                return controllerButton;

        throw new Exception("Now found button with ID " + String.valueOf(id));
    }

    @Override
    public void onClick(View view) {

        try {
            ControllerButton controllerButton = getControllerById(view.getId());
            controllerButton.buttonPressed();
            ionClickButton.onClickButton(view.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        ionLongPressViewElement.longPress(view.getId());
        return true;
    }
}
