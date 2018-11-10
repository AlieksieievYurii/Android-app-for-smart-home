package com.whitedeveloper.controlhome.controller.json;

import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.PinArduino;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreatorJsonByController
{
    private ArrayList<ControllerButton> arrayListControllerButton;
    private ArrayList<ControllerSeekBar> controllerSeekBarArrayList;

    public CreatorJsonByController(ArrayList<ControllerButton> arrayListControllerButton, ArrayList<ControllerSeekBar> controllerSeekBarArrayList) {
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
               return getJsonObjectByPinArduino(controllerButton.getPinArduino()).toString();

        throw new Exception("Error can not found button in ArrayList of ControllerButton with id " + id);
    }

    private JSONObject getJsonObjectByPinArduino(PinArduino pinArduino)
    {

        return CreatorJSON.getJsonPinArduinoObject(pinArduino.getTypePin(),
                                         pinArduino.getPin(),
                                         pinArduino.getValue());
    }

    private String getJsonSeekBarAction(int id) throws Exception {
        for(ControllerSeekBar controllerSeekBar : controllerSeekBarArrayList)
            if(controllerSeekBar.getId() == id)
                return getJsonObjectByPinArduino(controllerSeekBar.getPinArduino()).toString();

        throw  new Exception("Error can not found seek bar in ArrayList of ControllerSeekBar with id " + id);
    }

}
