package com.whitedeveloper.controlhome.controller;

import android.content.Context;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import com.whitedeveloper.custom.textview.ControllerTextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.whitedeveloper.TagKeys.*;

class SetterStatusViewFRomServer {
    static void setPropertyHashCode(Context context, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            ControllerSharedPreference.putHashCode(context, jsonObject.getString(PROPERTY_HASH_CODE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static void setButtonsStatus(ArrayList<ControllerButton> controllerButtons, String dataFromServer) throws JSONException {
        final JSONArray jsonArrayListActions = getArrayListOfSpecificElements(dataFromServer, PROPERTY_ACTIONS_FROM_SERVER);

        for (ControllerButton controllerButton : controllerButtons) {
            final JSONObject button = getJsonObjectByTCODPin(
                    controllerButton.getPinTCOD().getPin(),
                    jsonArrayListActions);
            if (button != null)
                controllerButton.setChecked(isDigitalPinON(button));
        }
    }

    static void setSeekBarsStatus(ArrayList<ControllerSeekBar> controllerSeekBars, String dataFromServer) throws JSONException {
        final JSONArray jsonArrayListActions = getArrayListOfSpecificElements(dataFromServer, PROPERTY_ACTIONS_FROM_SERVER);

        for (ControllerSeekBar controllerSeekBar : controllerSeekBars) {
            final JSONObject seekBar = getJsonObjectByTCODPin(
                    controllerSeekBar.getPinArduino().getPin(),
                    jsonArrayListActions);

            if (seekBar != null)
                controllerSeekBar.setValue(parseValue(seekBar));
        }
    }

    static void setTextViewSensors(ArrayList<ControllerTextView> arrayListControllersTextView, String dataFromServer) throws JSONException {
        final JSONArray jsonArraySensor = getArrayListOfSpecificElements(dataFromServer, PROPERTY_SENSORS_FROM_SERVER);

        for (ControllerTextView controllerTextView : arrayListControllersTextView) {
            final JSONObject jsonObject = getJsonObjectByNamePropertySensor(controllerTextView.getName(), jsonArraySensor);
            controllerTextView.setValue(jsonObject.getInt(PROPERTY_VALUE_FOR_SENSOR));
        }
    }

    private static JSONArray getArrayListOfSpecificElements(String dataFromServer, String property) throws JSONException {
        final JSONObject jsonObject = new JSONObject(dataFromServer);
        return jsonObject.getJSONArray(property);
    }

    private static int parseValue(JSONObject jsonObject) throws JSONException {
        return Integer.parseInt(jsonObject.getString(PROPERTY_VALUE_FOR_ACTIONS));
    }

    private static boolean isDigitalPinON(JSONObject jsonObject) throws JSONException {
        final String typePin = jsonObject.getString(PROPERTY_TYPE_PIN_FOR_ACTIONS);

        if (typePin.equals(PROPERTY_TYPE_DIGITAL_FOR_ACTIONS))
            return jsonObject.getString(PROPERTY_STATUS_PIN_FOR_ACTIONS).equals(PROPERTY_HIGH_LEVEL_FOR_ACTIONS);
        else throw new JSONException("Pin is not digital -> " + jsonObject.toString());
    }

    private static JSONObject getJsonObjectByTCODPin(int pin, JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            if (jsonObject.getInt(PROPERTY_PIN_FOR_ACTIONS) == pin)
                return jsonObject;
        }
        return null;
    }

    private static JSONObject getJsonObjectByNamePropertySensor(String nameProperty, JSONArray array) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            final JSONObject jsonObject = (JSONObject) array.get(i);
            if (jsonObject.getString(PROPERTY_SENSORS_FROM_SERVER).equals(nameProperty))
                return jsonObject;
        }
        throw new JSONException("Not found JSON object with name params " + nameProperty);
    }
}
