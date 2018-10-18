package com.whitedeveloper.controlhome.controller;

import com.whitedeveloper.custom.buttons.ControllerButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SetterStatusViewFRomServer {
    
    public static void setButtonsStatuc(ArrayList<ControllerButton> controllerButtons,String dataFromServer) throws JSONException {

        JSONArray jsonFromServer = new JSONArray(dataFromServer);

        for (ControllerButton controllerButton : controllerButtons) 
        {
            JSONObject jsonObject = getJsonObjectByArduinoPin(
                    controllerButton.getPinArduino().getPin(),
                    jsonFromServer);

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
