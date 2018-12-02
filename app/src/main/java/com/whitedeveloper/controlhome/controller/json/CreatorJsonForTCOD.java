package com.whitedeveloper.controlhome.controller.json;

import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.PinTCOD;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreatorJsonForTCOD
{
    private final ArrayList<ControllerButton> arrayListControllerButton;
    private final ArrayList<ControllerSeekBar> controllerSeekBarArrayList;

    public CreatorJsonForTCOD(ArrayList<ControllerButton> arrayListControllerButton, ArrayList<ControllerSeekBar> controllerSeekBarArrayList) {
        this.arrayListControllerButton = arrayListControllerButton;
        this.controllerSeekBarArrayList = controllerSeekBarArrayList;
    }

    public String getJsonOfElement(int id) throws Exception {
        try
        {
           return getJsonButtonAction(id);
        }catch (Exception error)
        {
            error.printStackTrace();
        }

        try {
            return getJsonSeekBarAction(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception("Not found element in Controllers with id " + id);
    }

    private String getJsonButtonAction(int id) throws Exception {
        for(ControllerButton controllerButton : arrayListControllerButton)
            if(controllerButton.getId() == id)
               return getJsonObjectByPinTCOD(controllerButton.getPinTCOD()).toString();

        throw new Exception("Error can not found button in ArrayList of ControllerButton with id " + id);
    }

    private JSONObject getJsonObjectByPinTCOD(PinTCOD pinTCOD)
    {

        return CreatorJSON.getJsonObjectPinTCOD(pinTCOD.getTypePin(),
                                         pinTCOD.getPin(),
                                         pinTCOD.getValue());
    }

    private String getJsonSeekBarAction(int id) throws Exception {
        for(ControllerSeekBar controllerSeekBar : controllerSeekBarArrayList)
            if(controllerSeekBar.getId() == id)
                return getJsonObjectByPinTCOD(controllerSeekBar.getPinArduino()).toString();

        throw  new Exception("Error can not found seek bar in ArrayList of ControllerSeekBar with id " + id);
    }

}
