package com.whitedeveloper.custom.buttons;

import android.view.View;
import android.widget.Button;
import com.whitedeveloper.custom.PinOfTCOD;

public class ControllerButton
{
    private long id;
    private final Button button;
    private final PinOfTCOD pinOfTCOD;
    private boolean checked = false;


    public ControllerButton(Button button, PinOfTCOD pinOfTCOD)
    {
        this.button = button;
        this.pinOfTCOD = pinOfTCOD;
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
            pinOfTCOD.setValue(1);
        }
        else
        {
            button.setActivated(false);
            pinOfTCOD.setValue(0);
        }
    }

    public void setChecked(boolean status)
    {
        this.checked = status;
        checkChecked();
    }

    public PinOfTCOD getPinOfTCOD() {
            return pinOfTCOD;
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
