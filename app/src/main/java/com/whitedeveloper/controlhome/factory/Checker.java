package com.whitedeveloper.controlhome.factory;

import android.content.Context;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.controlhome.factory.button.CreatorButton;
import org.json.JSONArray;
import org.json.JSONObject;

public class Checker {
    public static boolean checkId(int id, Context context)
    {
        try {
            JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);
            for(int i = 0; i < jsonArray.length(); i++)
                if(jsonArray.getJSONObject(i).getInt(CreatorButton.ATR_ID) == id)
                    return true;
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

    }

    public static boolean checkPin(int pin, Context context)
    {
        try {
            JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);

            for(int i = 0; i < jsonArray.length(); i++)
                if(jsonArray.getJSONObject(i).getInt(CreatorButton.ATR_PIN) == pin)
                    return true;
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
