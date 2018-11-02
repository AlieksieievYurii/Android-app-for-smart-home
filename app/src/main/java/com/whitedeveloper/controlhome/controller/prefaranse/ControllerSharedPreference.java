package com.whitedeveloper.controlhome.controller.prefaranse;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;

public class ControllerSharedPreference
{
    private static final String KEY_URL_PREFERENCE = "KEY_URL_PREFERENCE";
    private static final String KEY_JSON_FOR_CREATING_VIEWS_PREFERENCE = "JSON_FOR_CREATING_VIEWS";
    private static final String KEY_OBJECT_JSON_FOR_CREATING_VIEWS_PREFERENCE = "JSON_OBJECT_FOR_CREATING_VIEWS";
    private static final String KEY_OBJECT_URL_PREFERENCE = "KEY_OBJECT_URL_PREFERENCE";

    public static UrlPreference getUrlPreference(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_URL_PREFERENCE,Context.MODE_PRIVATE);
            return new UrlPreference(sharedPreferences.getString(KEY_OBJECT_URL_PREFERENCE,null));
    }

    public static void putUrlPreference(Context context, UrlPreference urlPreference)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_URL_PREFERENCE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_OBJECT_URL_PREFERENCE,urlPreference.convertToJson());
        editor.apply();
    }

    public static JSONArray getJsonForCreatingView(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_JSON_FOR_CREATING_VIEWS_PREFERENCE,Context.MODE_PRIVATE);
        return new JSONArray(sharedPreferences.getString(KEY_OBJECT_JSON_FOR_CREATING_VIEWS_PREFERENCE,null));
    }

    public static void putJsonForCreatingView(Context context, String json)
        {
            SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_JSON_FOR_CREATING_VIEWS_PREFERENCE,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_OBJECT_JSON_FOR_CREATING_VIEWS_PREFERENCE,json);
            editor.apply();
        }

}
