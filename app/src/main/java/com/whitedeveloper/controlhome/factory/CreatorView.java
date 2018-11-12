package com.whitedeveloper.controlhome.factory;

import android.content.Context;
import android.view.View;
import com.whitedeveloper.custom.PinArduino;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class CreatorView {

    private final JSONObject jsonObject;
    private final Context context;

    public Context getContext() {
        return context;
    }

    public CreatorView(Context context, JSONObject jsonObject)
    {
        this.jsonObject = jsonObject;
        this.context = context;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public abstract View createView() throws JSONException;
    public abstract PinArduino createPinArduino() throws JSONException;
}
