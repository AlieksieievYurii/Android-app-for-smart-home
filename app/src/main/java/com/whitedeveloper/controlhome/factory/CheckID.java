package com.whitedeveloper.controlhome.factory;

import android.content.Context;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.controlhome.factory.button.CreatorButton;
import org.json.JSONArray;

public class CheckID {
    public static boolean checkId(int id, Context context)
        {
            try {
                JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);
                for(int i = 0; i < jsonArray.length(); i++)
                    if(jsonArray.getJSONObject(i).getInt(CreatorButton.ATR_ID) == id)
                        return false;

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }

        }
}
