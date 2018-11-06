package com.whitedeveloper.controlhome.view.activitycreator.editorviews;

import android.content.Context;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.controlhome.factory.FactoryViews;
import com.whitedeveloper.controlhome.factory.button.CreatorButton;
import org.json.JSONArray;

public class FinderTypeElement
{
    public static String findTypeElementById(int id, Context context) throws Exception {
        JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(context);

        for(int i = 0; i < jsonArray.length(); i++)
        {
            if(jsonArray.getJSONObject(i).getInt(CreatorButton.ATR_ID) == id)
                return jsonArray.getJSONObject(i).getString(FactoryViews.TYPE_VIEW);
        }
        throw new Exception("Not found View with id " + id);
    }
}
