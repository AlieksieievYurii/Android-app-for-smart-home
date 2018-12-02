package com.whitedeveloper.controlhome.factory.button;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.whitedeveloper.controlhome.factory.CreatorView;
import com.whitedeveloper.controlhome.factory.style.ObtainStyle;
import com.whitedeveloper.controlhome.view.Icons;
import com.whitedeveloper.custom.PinTCOD;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import static com.whitedeveloper.TagKeys.*;

public class CreatorButton extends CreatorView {

    public CreatorButton(Context context,JSONObject jsonObject) {
        super(context,jsonObject);
    }

    @Override
    public View createView() throws JSONException {
        final Button button = new Button(getContext());
        button.setText(getJsonObject().getString(ATR_TEXT));
        button.setBackgroundResource(Objects.requireNonNull(Icons.getEnumByName(getJsonObject().getString(ATR_IMAGE_TYPE))).getDrawable());
        button.setId(getJsonObject().getInt(ATR_ID));
        ObtainStyle.setStyleButton(getContext(),button);
        return button;

    }

    @Override
    public PinTCOD createPinTCOD() throws JSONException {
        return new PinTCOD(PinTCOD.TYPE_PIN_DIGITAL,getJsonObject().getInt(ATR_PIN));
    }
}
