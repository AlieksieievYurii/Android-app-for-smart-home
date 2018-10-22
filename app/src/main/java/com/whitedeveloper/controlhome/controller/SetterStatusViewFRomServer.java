package com.whitedeveloper.controlhome.controller;

import com.whitedeveloper.custom.buttons.ControllerButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SetterStatusViewFRomServer {
    
    public static void setButtonsStatus(ArrayList<ControllerButton> controllerButtons, String dataFromServer) throws JSONException {

        JSONObject dataJson = new JSONObject(dataFromServer);
        JSONArray jsonArrayListActions = dataJson.getJSONArray("Actions");

        for (ControllerButton controllerButton : controllerButtons) 
        {
            JSONObject jsonObject = getJsonObjectByArduinoPin(
                    controllerButton.getPinArduino().getPin(),
                    jsonArrayListActions);

            controllerButton.setChecked(isDigitalPinON(jsonObject));
        }
        
    }
    
    private static boolean isDigitalPinON(JSONObject jsonObject) throws JSONException {
        String typePin = jsonObject.getString("T");
        
        if(typePin.equals("D"))
            return jsonObject.getString("S").equals("H");
        else throw new JSONException("Pin is not digital -> " + jsonObject.toString());
    }
    
    private static JSONObject getJsonObjectByArduinoPin(int pin, JSONArray jsonArray) throws JSONException {
        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            
            if(jsonObject.getInt("P") == pin )
                return jsonObject;
        }
        throw new JSONException("Not found JSONobject with pin " + pin);
    }
}
