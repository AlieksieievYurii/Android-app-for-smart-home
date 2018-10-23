package com.whitedeveloper.controlhome.controller;

import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class SetterStatusViewFRomServer
{
    private static final String PROPERTY_ACTIONS_FROM_SERVER = "Actions";
    private static final String PROPERTY_TYPE_PIN = "T";
    private static final String PROPERTY_TYPE_DIGITAL = "D";
    private static final String PROPERTY_PIN = "P";
    private static final String PROPERTY_STATUS_PIN = "S";
    private static final String PROPERTY_HIGH_LEVEL = "H";
    private static final String PROPERTY_VALUE = "V";


    static void setButtonsStatus(ArrayList<ControllerButton> controllerButtons, String dataFromServer) throws JSONException {

        JSONObject dataJson = new JSONObject(dataFromServer);
        JSONArray jsonArrayListActions = dataJson.getJSONArray(PROPERTY_ACTIONS_FROM_SERVER);

        for (ControllerButton controllerButton : controllerButtons)
        {
            JSONObject jsonObject = getJsonObjectByArduinoPin(
                    controllerButton.getPinArduino().getPin(),
                    jsonArrayListActions);

            controllerButton.setChecked(isDigitalPinON(jsonObject));
        }
    }

    static void setSeekBarsStatus(ArrayList<ControllerSeekBar> controllerSeekBars,String dataFromServer) throws JSONException {
        JSONObject jsonObject = new JSONObject(dataFromServer);
        JSONArray jsonArrayListActions = jsonObject.getJSONArray(PROPERTY_ACTIONS_FROM_SERVER);

        for(ControllerSeekBar controllerSeekBar : controllerSeekBars)
        {
            JSONObject seekBar = getJsonObjectByArduinoPin(
                    controllerSeekBar.getPinArduino().getPin(),
                    jsonArrayListActions);
            controllerSeekBar.setValue(parseValue(seekBar));
        }
    }

    private static int parseValue(JSONObject jsonObject) throws JSONException {
        return Integer.parseInt(jsonObject.getString(PROPERTY_VALUE));
    }
    
    private static boolean isDigitalPinON(JSONObject jsonObject) throws JSONException {
        String typePin = jsonObject.getString(PROPERTY_TYPE_PIN);
        
        if(typePin.equals(PROPERTY_TYPE_DIGITAL))
            return jsonObject.getString(PROPERTY_STATUS_PIN).equals(PROPERTY_HIGH_LEVEL);
        else throw new JSONException("Pin is not digital -> " + jsonObject.toString());
    }
    
    private static JSONObject getJsonObjectByArduinoPin(int pin, JSONArray jsonArray) throws JSONException {
        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            
            if(jsonObject.getInt(PROPERTY_PIN) == pin )
                return jsonObject;
        }
        throw new JSONException("Not found JSON object with pin " + pin);
    }
}
