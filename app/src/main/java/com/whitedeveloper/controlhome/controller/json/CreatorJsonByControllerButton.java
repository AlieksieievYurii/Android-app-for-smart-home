package com.whitedeveloper.controlhome.controller.json;

import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.PinArduino;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreatorJsonByControllerButton
{
    public static String getJsonActions(ArrayList<ControllerButton> arrayList)
    {
        JSONArray actions = new JSONArray();

        for(ControllerButton controllerButton : arrayList)
            actions.put(getJsonObjectByPinArduino(controllerButton.getPinArduino()));
        return actions.toString();
    }

    private static JSONObject getJsonObjectByPinArduino(PinArduino pinArduino)
    {

        return CreatorJSON.getJsonObject(pinArduino.getTypePin(),
                                         pinArduino.getPin(),
                                         pinArduino.getValue());
    }
}
