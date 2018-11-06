package com.whitedeveloper.controlhome.controller.json;

import org.json.JSONException;
import org.json.JSONObject;
import static com.whitedeveloper.custom.PinArduino.*;

class CreatorJSON
{



    static JSONObject getJsonPinArduinoObject(String type, int pin, int status)
    {
            try {
                if(type.equals(TYPE_PIN_DIGITAL))
                    return getDigitalPinAction(pin,status);
                else if(type.equals(TYPE_PIN_DIGITAL_ANALOG))
                    return getPWMpinAction(pin,status);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        return null;
    }

    private static JSONObject getDigitalPinAction(int pin, int status) throws JSONException {
        return new JSONObject().put(TYPE_PIN,TYPE_PIN_DIGITAL).put(PIN,pin).put(STATUS,status == 1?STATUS_HIGH:STATUS_LOW);
    }

    private static JSONObject getPWMpinAction(int pin, int value) throws JSONException {
            return new JSONObject().put(TYPE_PIN,TYPE_PIN_DIGITAL_ANALOG).put(PIN,pin).put(VALUE,value);
        }
}
