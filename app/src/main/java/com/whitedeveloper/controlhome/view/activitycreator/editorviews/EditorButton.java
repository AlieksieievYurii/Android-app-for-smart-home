package com.whitedeveloper.controlhome.view.activitycreator.editorviews;

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
import com.whitedeveloper.controlhome.factory.FactoryViews;
import com.whitedeveloper.controlhome.factory.button.CreatorButton;
import com.whitedeveloper.custom.PinTCOD;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.whitedeveloper.controlhome.view.activitycreator.fragments.FragmentButton.NAME_ICONS;

class EditorButton {
    private final AppCompatActivity appCompatActivity;

    private final int originId;
    private int originPin;
    private EditText edtName;
    private EditText edtPinController;
    private Spinner spImageType;
    private TextView tvError;

    private Button btnExample;

    private TextView tvExampleJSON;

    private String name;
    private String pin;
    private String imageType;

    EditorButton(AppCompatActivity appCompatActivity, int id) {
        this.originId = id;
        this.appCompatActivity = appCompatActivity;
        appCompatActivity.setContentView(R.layout.fragment_button);

        init();
        setAllOldFields();
    }

    private void init() {

        edtName = appCompatActivity.findViewById(R.id.edt_name);
        edtPinController = appCompatActivity.findViewById(R.id.edt_pin_controller);
        spImageType = appCompatActivity.findViewById(R.id.sp_image_type);
        tvExampleJSON = appCompatActivity.findViewById(R.id.tv_example_json);
        tvError = appCompatActivity.findViewById(R.id.tv_error);

        btnExample = appCompatActivity.findViewById(R.id.btn_example);
        btnExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnExample.setActivated(!btnExample.isActivated());
                showExampleJson();
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

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(appCompatActivity.getBaseContext(), android.R.layout.simple_spinner_item, NAME_ICONS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spImageType.setAdapter(adapter);
        spImageType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imageType = NAME_ICONS[i];
                showExampleButton();
                showExampleJson();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void saveJsonForCreatingViews() {
        try {
            EditorViewsJson.saveChangedJsonForCreatingView(getJSON(), appCompatActivity.getBaseContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showExampleButton() {
        btnExample.setText(name);
        btnExample.setBackgroundResource(CreatorButton.getBackgroundResource(imageType));
    }

    private void showExampleJson() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append("\n");
        stringBuilder.append("  \"").append(PinTCOD.TYPE_PIN).append("\":\"").append(PinTCOD.TYPE_PIN_DIGITAL).append("\",\n");
        stringBuilder.append("  \"").append(PinTCOD.PIN).append("\":").append(pin).append(",\n");
        stringBuilder.append("  \"").append(PinTCOD.STATUS).append("\":\"").append(btnExample.isActivated() ? PinTCOD.STATUS_HIGH : PinTCOD.STATUS_LOW).append("\"\n");
        stringBuilder.append("}");

        tvExampleJSON.setText(stringBuilder);
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
        jsonObject.put(FactoryViews.TYPE_VIEW, FactoryViews.TYPE_VIEW_BUTTON);
        jsonObject.put(CreatorButton.ATR_ID, originId);
        jsonObject.put(CreatorButton.ATR_TEXT, name);
        jsonObject.put(CreatorButton.ATR_PIN, Integer.parseInt(pin));
        jsonObject.put(CreatorButton.ATR_IMAGE_TYPE, imageType);

        return jsonObject;
    }

    private void setAllOldFields() {
        try {
            final JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(appCompatActivity.getBaseContext());
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).getInt(CreatorButton.ATR_ID) == originId) {
                    final JSONObject jsonObject = jsonArray.getJSONObject(i);

                    imageType = jsonObject.getString(CreatorButton.ATR_IMAGE_TYPE);
                    setImageButtonExample(imageType);

                    originPin = jsonObject.getInt(CreatorButton.ATR_PIN);

                    edtName.setText(jsonObject.getString(CreatorButton.ATR_TEXT));
                    edtPinController.setText(String.valueOf(originPin));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setImageButtonExample(String nameImageType) {
        for (int i = 0; i < NAME_ICONS.length; i++) {
            if (NAME_ICONS[i].equals(nameImageType))
                spImageType.setSelection(i);
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
            showExampleButton();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
