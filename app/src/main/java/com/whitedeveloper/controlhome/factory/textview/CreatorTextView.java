package com.whitedeveloper.controlhome.factory.textview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.factory.CreatorView;
import com.whitedeveloper.controlhome.factory.style.ObtainStyle;
import com.whitedeveloper.custom.PinOfTCOD;
import org.json.JSONException;
import org.json.JSONObject;

import static com.whitedeveloper.TagKeys.*;

public class CreatorTextView extends CreatorView
{
    public static final String TEMPERATURE = "temperature";
    public static final String STATE_DAY = "stateDay";


    public CreatorTextView(Context context, JSONObject jsonObject) {
        super(context, jsonObject);
    }

    public static int getBackgroundResource(String type)
    {
        switch (type)
        {
            case TEMPERATURE: return R.drawable.ic_celsius;
            case STATE_DAY: return R.drawable.state_day;
            default: return 0;
        }
    }

    @Override
    public View createView() throws JSONException
    {
        final TextView textView = new TextView(getContext());
        textView.setId(getJsonObject().getInt(ATR_ID));
        textView.setBackgroundResource(getBackgroundResource(getJsonObject().getString(ATR_IMAGE_TYPE)));
        ObtainStyle.setStyleTextView(getContext(),textView);
        return textView;
    }

    @Override
    public PinOfTCOD createPinTCOD(){
        return null;
    }

    public String getNameSensorTCOD() throws JSONException {
        return getJsonObject().getString(ATR_NAME_SENSOR);
    }

}
