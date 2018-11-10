package com.whitedeveloper.controlhome.controller;

import android.content.Context;
import android.util.Log;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import com.whitedeveloper.custom.textview.ControllerTextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class SetterStatusViewFRomServer
{
    private static final String PROPERTY_HASH_CODE = "HashCode";
    private static final String PROPERTY_ACTIONS_FROM_SERVER = "Actions";
    private static final String PROPERTY_SENSORS_FROM_SERVER = "ParamsFromArduino";
    private static final String PROPERTY_TYPE_PIN_FOR_ACTIONS = "T";
    private static final String PROPERTY_TYPE_DIGITAL_FOR_ACTIONS = "D";
    private static final String PROPERTY_PIN_FOR_ACTIONS = "P";
    private static final String PROPERTY_STATUS_PIN_FOR_ACTIONS = "S";
    private static final String PROPERTY_HIGH_LEVEL_FOR_ACTIONS = "H";
    private static final String PROPERTY_VALUE_FOR_ACTIONS = "V";
    private static final String PROPERTY_VALUE_FOR_SENSOR = "Value";


    static void setPropertyHashCode(Context context, String data)
    {
        try {
            JSONObject jsonObject = new JSONObject(data);
            Log.i("TEST",jsonObject.getString(PROPERTY_HASH_CODE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static void setButtonsStatus(ArrayList<ControllerButton> controllerButtons, String dataFromServer) throws JSONException {
        JSONArray jsonArrayListActions = getArrayListOfSpecificElements(dataFromServer,PROPERTY_ACTIONS_FROM_SERVER);

        for (ControllerButton controllerButton : controllerButtons)
        {
            JSONObject jsonObject = getJsonObjectByArduinoPin(
                    controllerButton.getPinArduino().getPin(),
                    jsonArrayListActions);

            controllerButton.setChecked(isDigitalPinON(jsonObject));
        }
    }

    static void setSeekBarsStatus(ArrayList<ControllerSeekBar> controllerSeekBars,String dataFromServer) throws JSONException {
        JSONArray jsonArrayListActions = getArrayListOfSpecificElements(dataFromServer,PROPERTY_ACTIONS_FROM_SERVER);

        for(ControllerSeekBar controllerSeekBar : controllerSeekBars)
        {
            JSONObject seekBar = getJsonObjectByArduinoPin(
                    controllerSeekBar.getPinArduino().getPin(),
                    jsonArrayListActions);
            controllerSeekBar.setValue(parseValue(seekBar));
        }
    }

    static void setTextViewSensors(ArrayList<ControllerTextView> arrayListControllersTextView,String dataFromServer) throws JSONException {
        JSONArray jsonArraySensor = getArrayListOfSpecificElements(dataFromServer,PROPERTY_SENSORS_FROM_SERVER);

        for(ControllerTextView controllerTextView : arrayListControllersTextView)
        {
            JSONObject jsonObject = getJsonObjectByNamePropertySensor(controllerTextView.getName(),jsonArraySensor);
            controllerTextView.setValue(jsonObject.getInt(PROPERTY_VALUE_FOR_SENSOR));
        }
    }

    private static JSONArray getArrayListOfSpecificElements(String dataFromServer, String property) throws JSONException {
        JSONObject jsonObject = new JSONObject(dataFromServer);
        return jsonObject.getJSONArray(property);
    }

    private static int parseValue(JSONObject jsonObject) throws JSONException {
        return Integer.parseInt(jsonObject.getString(PROPERTY_VALUE_FOR_ACTIONS));
    }
    
    private static boolean isDigitalPinON(JSONObject jsonObject) throws JSONException {
        String typePin = jsonObject.getString(PROPERTY_TYPE_PIN_FOR_ACTIONS);
        
        if(typePin.equals(PROPERTY_TYPE_DIGITAL_FOR_ACTIONS))
            return jsonObject.getString(PROPERTY_STATUS_PIN_FOR_ACTIONS).equals(PROPERTY_HIGH_LEVEL_FOR_ACTIONS);
        else throw new JSONException("Pin is not digital -> " + jsonObject.toString());
    }
    
    private static JSONObject getJsonObjectByArduinoPin(int pin, JSONArray jsonArray) throws JSONException {
        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            
            if(jsonObject.getInt(PROPERTY_PIN_FOR_ACTIONS) == pin )
                return jsonObject;
        }
        throw new JSONException("Not found JSON object with pin " + pin);
    }

    private static JSONObject getJsonObjectByNamePropertySensor(String nameProperty, JSONArray array) throws JSONException {
        for(int i = 0; i < array.length(); i++)
        {
            JSONObject jsonObject = (JSONObject) array.get(i);
            if(jsonObject.getString("ParamsFromArduino").equals(nameProperty))
                return jsonObject;
        }
        throw new JSONException("Not found JSON object with name params " + nameProperty);
    }
}
