package com.whitedeveloper.controlhome.factory;

import android.content.Context;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.whitedeveloper.TagKeys.*;

public class Checker {
    public static boolean checkId(int id, Context context)
    {
        try {
            final JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);

            for(int i = 0; i < jsonArray.length(); i++)
                if(jsonArray.getJSONObject(i).getInt(ATR_ID) == id)
                    return true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        return false;
    }

    public static boolean checkPin(int pin, Context context)
    {
        try {
            final JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (!jsonObject.getString(TYPE_VIEW).equals(TYPE_VIEW_TEXT_VIEW)
                        && jsonObject.getInt(ATR_PIN) == pin)

                        return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
