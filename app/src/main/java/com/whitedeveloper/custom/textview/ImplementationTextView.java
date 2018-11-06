package com.whitedeveloper.custom.textview;

import android.view.View;
import com.whitedeveloper.custom.IonLongPressViewElement;

import java.util.ArrayList;

public class ImplementationTextView implements View.OnLongClickListener {

    private ArrayList<ControllerTextView> controllerTextViewArrayList;
    private IonLongPressViewElement ionLongPressViewElement;

    public ImplementationTextView(ArrayList<ControllerTextView> controllerTextViewArrayList, IonLongPressViewElement ionLongPressViewElement) {
        this.controllerTextViewArrayList = controllerTextViewArrayList;
        this.ionLongPressViewElement = ionLongPressViewElement;

        setTextViewListener();
    }

    private void setTextViewListener()
    {
        for(ControllerTextView controllerTextView : controllerTextViewArrayList)
        {
            controllerTextView.setOnLongClickListener(this);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        ionLongPressViewElement.longPress(view.getId());
        return true;
    }
}
