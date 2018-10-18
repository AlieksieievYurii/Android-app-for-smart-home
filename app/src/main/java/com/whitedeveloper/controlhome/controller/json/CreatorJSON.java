package com.whitedeveloper.controlhome.controller.json;

import org.json.JSONException;
import org.json.JSONObject;

public class CreatorJSON
{
    public static JSONObject getJsonObject(char type, int pin, int status)
    {
            try {
                if(type == 'D')
                    return getDigitalPinAction(pin,status);
                else if(type == 'A')
                    return getPWMpinAction(pin,status);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        return null;
    }

    private static JSONObject getDigitalPinAction(int pin, int status) throws JSONException {
        return new JSONObject().put("T","D").put("P",pin).put("S",status == 1?"H":"L");
    }

    private static JSONObject getPWMpinAction(int pin, int value) throws JSONException {
            return new JSONObject().put("T","A").put("P",pin).put("V",value);
        }
}
