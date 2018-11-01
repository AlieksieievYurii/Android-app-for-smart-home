package com.whitedeveloper.custom.buttons;

import android.view.View;
import android.widget.Button;
import com.whitedeveloper.custom.PinArduino;

public class ControllerButton
{
    private long id;
    private Button button;
    private boolean checked = false;
    private PinArduino pinArduino;


    public ControllerButton(Button button, PinArduino pinArduino)
    {
        this.button = button;
        this.pinArduino = pinArduino;
        this.id  = button.getId();
    }


    void buttonPressed()
    {
        checked = !checked;
        checkChecked();
    }

    private void checkChecked()
    {
        if(checked)
        {
            button.setActivated(true);
            pinArduino.setValue(1);
        }
        else
        {
            button.setActivated(false);
            pinArduino.setValue(0);
        }
    }

    public void setChecked(boolean status)
    {
        this.checked = status;
        checkChecked();
    }

    public PinArduino getPinArduino() {
            return pinArduino;
        }
    void setOnClickLIstener(View.OnClickListener onClickLIstener)
    {
        button.setOnClickListener(onClickLIstener);
    }
    public Button getButton() {
          return button;
      }
    public boolean getChecked()
       {
           return checked;
       }
    public long getId() {
           return id;
       }




}
