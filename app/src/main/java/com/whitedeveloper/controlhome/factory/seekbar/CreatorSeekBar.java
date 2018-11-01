package com.whitedeveloper.controlhome.factory.seekbar;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.content.Context;
import android.view.View;
import com.whitedeveloper.controlhome.factory.CreatorView;
import com.whitedeveloper.controlhome.factory.style.ObtainStyle;
import com.whitedeveloper.custom.PinArduino;
import org.json.JSONException;
import org.json.JSONObject;

public class CreatorSeekBar extends CreatorView {

    public static final String ATR_ID = "id";
    public static final String ATR_PIN = "pin";

    public CreatorSeekBar(Context context, JSONObject jsonObject) {
        super(context, jsonObject);
    }

    @Override
    public View createView() throws JSONException
    {
        BoxedVertical boxedVertical = new BoxedVertical(getContext());
        boxedVertical.setId(getJsonObject().getInt(ATR_ID));
        ObtainStyle.setStyleSeekBar(getContext(),boxedVertical);

        return boxedVertical;
    }

    @Override
    public PinArduino createPinArduino() throws JSONException {
        return new PinArduino('A',getJsonObject().getInt(ATR_PIN));
    }
}
