package com.whitedeveloper.controlhome.view.activitycreator.editorviews;

import android.content.Context;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import org.json.JSONArray;

import static com.whitedeveloper.TagKeys.ATR_ID;
import static com.whitedeveloper.TagKeys.TYPE_VIEW;

class FinderTypeElement
{
    static String findTypeElementById(int id, Context context) throws Exception {
        JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);

        for(int i = 0; i < jsonArray.length(); i++)
        {
            if(jsonArray.getJSONObject(i).getInt(ATR_ID) == id)
                return jsonArray.getJSONObject(i).getString(TYPE_VIEW);
        }
        throw new Exception("Not found View with id " + id);
    }
}
