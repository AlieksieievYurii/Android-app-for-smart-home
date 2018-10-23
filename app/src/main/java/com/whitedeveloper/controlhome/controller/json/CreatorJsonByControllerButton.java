package com.whitedeveloper.controlhome.controller.json;

import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.PinArduino;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreatorJsonByControllerButton
{
    public static String getJsonButtonsActions(ArrayList<ControllerButton> arrayList)
    {
        JSONArray actions = new JSONArray();

        for(ControllerButton controllerButton : arrayList)
            actions.put(getJsonObjectByPinArduino(controllerButton.getPinArduino()));
        return actions.toString();
    }

    private static JSONObject getJsonObjectByPinArduino(PinArduino pinArduino)
    {

        return CreatorJSON.getJsonPinArduinoObject(pinArduino.getTypePin(),
                                         pinArduino.getPin(),
                                         pinArduino.getValue());
    }

    public static String getReadyJsonActionsForArduino(ArrayList<ControllerButton> arrayListControllersButton,ArrayList<ControllerSeekBar> controllerSeekBarArrayList)
    {
        JSONArray actions = new JSONArray();

        for(ControllerButton controllerButton : arrayListControllersButton)
                   actions.put(getJsonObjectByPinArduino(controllerButton.getPinArduino()));

        for(ControllerSeekBar controllerSeekBar : controllerSeekBarArrayList)
                    actions.put(getJsonObjectByPinArduino(controllerSeekBar.getPinArduino()));

        return actions.toString();
    }

    public static String getJsonSeekBarsActions(ArrayList<ControllerSeekBar> controllerSeekBarArrayList)
    {
        JSONArray actions = new JSONArray();

        for(ControllerSeekBar controllerSeekBar : controllerSeekBarArrayList)
            actions.put(getJsonObjectByPinArduino(controllerSeekBar.getPinArduino()));
        return actions.toString();
    }
}
