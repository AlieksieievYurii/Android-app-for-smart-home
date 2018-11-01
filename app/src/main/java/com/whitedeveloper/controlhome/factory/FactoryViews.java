package com.whitedeveloper.controlhome.factory;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.whitedeveloper.controlhome.factory.button.CreatorButton;
import com.whitedeveloper.controlhome.factory.button.IcreateView;
import com.whitedeveloper.controlhome.factory.seekbar.CreatorSeekBar;
import com.whitedeveloper.custom.PinArduino;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FactoryViews
{
    private static final String TYPE_VIEW = "type_view" ;
    private static final String TYPE_VIEW_BUTTON = "type_view_button" ;
    private static final String TYPE_VIEW_SEEK_BAR = "type_view_seek_bar";
    private static final String TYPE_VIEW_TEXT_VIEW = "type_view_text_view";
    private IcreateView icreateView;
    private CreatorView creatorView;
    private Context context;

    public FactoryViews(Context context, IcreateView iCreateButton)
    {
        this.icreateView = iCreateButton;
        this.context = context;
    }

    public void createViews(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);

        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            switch (jsonObject.getString(TYPE_VIEW))
                    {
                        case TYPE_VIEW_BUTTON:createButton(jsonObject);break;
                        case TYPE_VIEW_SEEK_BAR:createSeekBar(jsonObject); break;
                        case TYPE_VIEW_TEXT_VIEW:/*TODO Implement factory for textview(Sensors)*/break;
                        default: throw new JSONException("Unknown type of view!");
                    }
        }

    }

    private void createButton(JSONObject jsonObject) throws JSONException {
        creatorView = new CreatorButton(context,jsonObject);
        View view = creatorView.createView();
        PinArduino pinArduino = creatorView.createPinArduino();
        icreateView.createButton(new ControllerButton((Button)view,pinArduino));
    }

    private void createSeekBar(JSONObject jsonObject) throws JSONException {
        creatorView = new CreatorSeekBar(context,jsonObject);
        View view = creatorView.createView();
        PinArduino pinArduino = creatorView.createPinArduino();
        icreateView.createSeekBar(new ControllerSeekBar((BoxedVertical)view,pinArduino));
    }

}
