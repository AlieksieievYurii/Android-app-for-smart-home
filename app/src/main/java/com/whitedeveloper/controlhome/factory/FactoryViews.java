package com.whitedeveloper.controlhome.factory;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.whitedeveloper.controlhome.factory.button.CreatorButton;
import com.whitedeveloper.controlhome.factory.seekbar.CreatorSeekBar;
import com.whitedeveloper.controlhome.factory.textview.CreatorTextView;
import com.whitedeveloper.custom.PinTCOD;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import com.whitedeveloper.custom.textview.ControllerTextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.whitedeveloper.TagKeys.*;

public class FactoryViews {
    private final IcreateView icreateView;
    private CreatorView creatorView;
    private final Context context;

    public FactoryViews(Context context, IcreateView icreateView) {
        this.icreateView = icreateView;
        this.context = context;
    }

    public void createViews(JSONArray jsonArray) throws JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            switch (jsonObject.getString(TYPE_VIEW)) {
                case TYPE_VIEW_BUTTON:
                    createButton(jsonObject);
                    break;
                case TYPE_VIEW_SEEK_BAR:
                    createSeekBar(jsonObject);
                    break;
                case TYPE_VIEW_TEXT_VIEW:
                    createTextView(jsonObject);
                    break;
                default:
                    throw new JSONException("Unknown type of view!");
            }
            if(i == jsonArray.length() - 1)
                icreateView.finishCreating();
        }

    }

    private void createButton(JSONObject jsonObject) throws JSONException {
        creatorView = new CreatorButton(context, jsonObject);
        final View view = creatorView.createView();
        final PinTCOD pinTCOD = creatorView.createPinTCOD();
        icreateView.createButton(new ControllerButton((Button) view, pinTCOD));
    }

    private void createSeekBar(JSONObject jsonObject) throws JSONException {
        creatorView = new CreatorSeekBar(context, jsonObject);
        final View view = creatorView.createView();
        final PinTCOD pinTCOD = creatorView.createPinTCOD();
        icreateView.createSeekBar(new ControllerSeekBar(((CreatorSeekBar) creatorView).getBoxedVertical(), (LinearLayout) view, pinTCOD));
    }

    private void createTextView(JSONObject jsonObject) throws JSONException {
        creatorView = new CreatorTextView(context, jsonObject);
        final View view = creatorView.createView();
        final String nameSensor = ((CreatorTextView) creatorView).getNameSensorTCOD();
        icreateView.createTextView(new ControllerTextView((TextView) view, nameSensor));
    }

}
