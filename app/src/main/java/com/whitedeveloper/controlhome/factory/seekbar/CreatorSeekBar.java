package com.whitedeveloper.controlhome.factory.seekbar;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.whitedeveloper.controlhome.factory.CreatorView;
import com.whitedeveloper.controlhome.factory.style.ObtainStyle;
import com.whitedeveloper.custom.PinArduino;
import org.json.JSONException;
import org.json.JSONObject;

public class CreatorSeekBar extends CreatorView {

    public static final String ATR_ID = "id";
    public static final String ATR_PIN = "pin";
    public static final String ATR_NAME = "name";

    private BoxedVertical boxedVertical;

    public CreatorSeekBar(Context context, JSONObject jsonObject) {
        super(context, jsonObject);
    }

    @Override
    public View createView() throws JSONException {
        LinearLayout linearLayout = new LinearLayout(getContext());
        ObtainStyle.setStyleLayoutSeekBar(getContext(),linearLayout);

        TextView textView = new TextView(getContext());
        try {
            textView.setText(getJsonObject().getString(ATR_NAME));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ObtainStyle.setStyleTextViewForSeekBar(getContext(),textView);

        boxedVertical = new BoxedVertical(getContext());
        linearLayout.setId(getJsonObject().getInt(ATR_ID));
        boxedVertical.setId(getJsonObject().getInt(ATR_ID));
        ObtainStyle.setStyleSeekBar(getContext(),boxedVertical);

        linearLayout.addView(boxedVertical);
        linearLayout.addView(textView);

        return linearLayout;
    }

    public BoxedVertical getBoxedVertical() {
        return boxedVertical;
    }

    @Override
    public PinArduino createPinArduino() throws JSONException {
        return new PinArduino(PinArduino.TYPE_PIN_DIGITAL_ANALOG,getJsonObject().getInt(ATR_PIN));
    }
}
