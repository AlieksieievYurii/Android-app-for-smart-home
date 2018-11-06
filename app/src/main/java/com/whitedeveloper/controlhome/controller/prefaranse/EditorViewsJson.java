package com.whitedeveloper.controlhome.controller.prefaranse;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;

public class EditorViewsJson
{
    public static void removeViewWithID(int id,Context context) throws Exception {
        JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);

        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            if(jsonObject.getInt("id") == id) {
                jsonArray.remove(i);
                ControllerSharedPreference.putJsonForCreatingView(context,jsonArray.toString());
            }
        }
    }

    public static void saveJsonForCreatingViews(JSONObject json, Context context)
    {
        try {
            JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);
            jsonArray.put(json);
            ControllerSharedPreference.putJsonForCreatingView(context,jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(json);

            ControllerSharedPreference.putJsonForCreatingView(context,jsonArray.toString());
        }
    }
}
