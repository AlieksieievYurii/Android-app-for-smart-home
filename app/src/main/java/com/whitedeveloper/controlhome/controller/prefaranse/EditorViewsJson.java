package com.whitedeveloper.controlhome.controller.prefaranse;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.whitedeveloper.TagKeys.ATR_ID;

public class EditorViewsJson
{
    public static void removeViewWithID(int id,Context context) throws Exception {
        final JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);

        for(int i = 0; i < jsonArray.length(); i++)
        {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);

            if(jsonObject.getInt(ATR_ID) == id) {
                jsonArray.remove(i);
                ControllerSharedPreference.putJsonForCreatingView(context,jsonArray.toString());
            }
        }
    }

    public static void saveJsonForCreatingViews(JSONObject json, Context context)
    {
        try {
            final JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);
            jsonArray.put(json);
            ControllerSharedPreference.putJsonForCreatingView(context,jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(json);

            ControllerSharedPreference.putJsonForCreatingView(context,jsonArray.toString());
        }
    }

    public static void saveChangedJsonForCreatingView(JSONObject jsonObject,Context context)
    {
        try {
            final JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);
            final int index = getIndexOfObjectInJsonById(jsonArray,jsonObject);
            jsonArray.put(index,jsonObject);
            ControllerSharedPreference.putJsonForCreatingView(context,jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getIndexOfObjectInJsonById(JSONArray jsonArray, JSONObject jsonObject) throws Exception {
        for(int i = 0; i < jsonArray.length(); i++)
            if(jsonArray.getJSONObject(i).getInt(ATR_ID) == jsonObject.getInt(ATR_ID))
                return i;
        throw new Exception("Not found jsonObject with id " + jsonObject.getInt(ATR_ID));
    }
}
