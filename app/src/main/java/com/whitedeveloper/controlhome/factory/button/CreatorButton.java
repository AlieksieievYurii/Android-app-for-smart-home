package com.whitedeveloper.controlhome.factory.button;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.factory.CreatorView;
import com.whitedeveloper.controlhome.factory.style.ObtainStyle;
import com.whitedeveloper.custom.PinArduino;
import org.json.JSONException;
import org.json.JSONObject;

public class CreatorButton extends CreatorView {

    private static final String ATR_TEXT = "text";
    private static final String ATR_IMAGE_TYPE = "typeImage";
    private static final String ATR_PIN = "pin";
    private static final String ATR_ID = "id";

    private static final String COMPUTER = "computer";
    private static final String LAMP = "lamp";
    private static final String FAN = "fan";
    private static final String SOCKET = "socket";

    public CreatorButton(Context context,JSONObject jsonObject) {
        super(context,jsonObject);
    }

    private int getBackgroundResource(String type)
    {
        switch (type)
        {
            case LAMP: return R.drawable.lamp;
            case COMPUTER: return R.drawable.computer;
            case FAN: return R.drawable.fan;
            case SOCKET: return R.drawable.socket;
            default: return 0;
        }
    }

    @Override
    public View createView() throws JSONException {
        Button button = new Button(getContext());
        button.setText(getJsonObject().getString(ATR_TEXT));
        button.setBackgroundResource(getBackgroundResource(getJsonObject().getString(ATR_IMAGE_TYPE)));
        button.setId(getJsonObject().getInt(ATR_ID));
        ObtainStyle.setStyleButton(getContext(),button);
        return button;

    }

    @Override
    public PinArduino createPinArduino() throws JSONException {
        return new PinArduino('D',getJsonObject().getInt(ATR_PIN));
    }
}