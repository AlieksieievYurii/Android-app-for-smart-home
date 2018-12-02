package com.whitedeveloper.custom.buttons;

import android.view.View;
import android.widget.Button;
import com.whitedeveloper.custom.PinTCOD;

public class ControllerButton
{
    private long id;
    private final Button button;
    private final PinTCOD pinTCOD;
    private boolean checked = false;


    public ControllerButton(Button button, PinTCOD pinTCOD)
    {
        this.button = button;
        this.pinTCOD = pinTCOD;
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
            pinTCOD.setValue(1);
        }
        else
        {
            button.setActivated(false);
            pinTCOD.setValue(0);
        }
    }

    public void setChecked(boolean status)
    {
        this.checked = status;
        checkChecked();
    }

    public PinTCOD getPinTCOD() {
            return pinTCOD;
        }
    void setOnClickListener(View.OnClickListener onClickLIstener)
    {
        button.setOnClickListener(onClickLIstener);
    }
    void setOnLongClickListener(View.OnLongClickListener onClickListener)
    {
        button.setOnLongClickListener(onClickListener);
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
