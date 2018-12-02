package com.whitedeveloper.controlhome.view.activitycreator.editorviews;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.controlhome.controller.prefaranse.EditorViewsJson;
import com.whitedeveloper.controlhome.factory.Checker;
import com.whitedeveloper.custom.PinOfTCOD;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.whitedeveloper.TagKeys.*;


class EditorSeekBar {
    private final AppCompatActivity appCompatActivity;

    private EditText edtName;
    private EditText edtPinController;
    private TextView tvExampleJson;
    private TextView tvTextOfSeekBar;
    private TextView tvError;

    private final int originId;
    private int originPin;
    private String name;
    private String pin;
    private int value = 0;

    EditorSeekBar(AppCompatActivity appCompatActivity, int id) {
        this.originId = id;
        this.appCompatActivity = appCompatActivity;
        appCompatActivity.setContentView(R.layout.fragment_seek_bar);

        init();
        setAllOldFields();
    }

    private void init() {

        edtName = appCompatActivity.findViewById(R.id.edt_name);
        edtPinController = appCompatActivity.findViewById(R.id.edt_pin_controller);
        tvError = appCompatActivity.findViewById(R.id.tv_error);
        tvExampleJson = appCompatActivity.findViewById(R.id.tv_example_json);
        tvTextOfSeekBar = appCompatActivity.findViewById(R.id.tv_text_seek_bar);

        final BoxedVertical boxedVerticalExample = appCompatActivity.findViewById(R.id.bv_example);
        boxedVerticalExample.setOnBoxedPointsChangeListener(new BoxedVertical.OnValuesChangeListener() {
            @Override
            public void onPointsChanged(BoxedVertical boxedVertical, int i) {
                showExampleJson();
                showExampleSeekBar();
                value = i;
            }

            @Override
            public void onStartTrackingTouch(BoxedVertical boxedVertical) {

            }

            @Override
            public void onStopTrackingTouch(BoxedVertical boxedVertical) {

            }
        });

        final TypingListener typingListener = new TypingListener();
        edtName.addTextChangedListener(typingListener);
        edtPinController.addTextChangedListener(typingListener);

        final Button btnApply = appCompatActivity.findViewById(R.id.btn_add_new_view);
        btnApply.setText(R.string.text_editor_views_btn_apply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValues()) {
                    saveJsonForCreatingViews();
                    appCompatActivity.setResult(Activity.RESULT_OK);
                    appCompatActivity.finish();
                }
            }
        });

    }

    private void showExampleSeekBar() {
        tvTextOfSeekBar.setText(name);
    }

    private void showExampleJson() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append("\n");
        stringBuilder.append("  \"").append(PinOfTCOD.TYPE_PIN).append("\":\"").append(PinOfTCOD.TYPE_PIN_DIGITAL_ANALOG).append("\",\n");
        stringBuilder.append("  \"").append(PinOfTCOD.PIN).append("\":").append(pin).append(",\n");
        stringBuilder.append("  \"").append(PinOfTCOD.STATUS).append("\":").append(value).append("\n");
        stringBuilder.append("}");
        tvExampleJson.setText(stringBuilder);
    }

    private boolean checkValues() {
        if (pin.trim().equals("")) {
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(R.string.pin_can_be_empty);
            return false;
        } else if (!String.valueOf(originPin).equals(pin) && Checker.checkPin(Integer.parseInt(pin), appCompatActivity)) {
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(R.string.pin_existed);
            return false;
        }

        return true;
    }

    private JSONObject getJSON() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put(TYPE_VIEW, TYPE_VIEW_SEEK_BAR);
        jsonObject.put(ATR_ID, originId);
        jsonObject.put(ATR_NAME, name);
        jsonObject.put(ATR_PIN, Integer.parseInt(pin));

        return jsonObject;
    }

    private void saveJsonForCreatingViews() {
        try {
            EditorViewsJson.saveChangedJsonForCreatingView(getJSON(), appCompatActivity.getBaseContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setAllOldFields() {
        try {
            final JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(appCompatActivity.getBaseContext());
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).getInt(ATR_ID) == originId) {
                    final JSONObject jsonObject = jsonArray.getJSONObject(i);

                    originPin = jsonObject.getInt(ATR_PIN);

                    edtPinController.setText(String.valueOf(originPin));
                    edtName.setText(jsonObject.getString(ATR_NAME));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TypingListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {
            tvError.setVisibility(View.GONE);
            name = edtName.getText().toString();
            pin = edtPinController.getText().toString();

            showExampleJson();
            showExampleSeekBar();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
