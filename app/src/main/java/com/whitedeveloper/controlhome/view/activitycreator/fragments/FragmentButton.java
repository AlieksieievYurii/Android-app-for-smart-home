package com.whitedeveloper.controlhome.view.activitycreator.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.prefaranse.EditorViewsJson;
import com.whitedeveloper.controlhome.factory.Checker;
import com.whitedeveloper.controlhome.factory.FactoryViews;
import com.whitedeveloper.controlhome.factory.button.CreatorButton;
import com.whitedeveloper.controlhome.view.activitycreator.ActivityCreateNewElement;
import com.whitedeveloper.custom.PinTCOD;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;

public class FragmentButton extends Fragment {

    public static final String[] NAME_ICONS = {CreatorButton.LAMP, CreatorButton.COMPUTER, CreatorButton.FAN, CreatorButton.SOCKET};


    private Button btnExample;
    private TextView tvExampleJson;
    private EditText edtName;
    private EditText edtPin;
    private TextView tvError;

    private int id;
    private String name = "";
    private String pin = "";
    private String imageType = "";

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_button, container, false);
        init();
        setRandomId();
        return view;

    }

    private void setRandomId() {
        do {
            id = new Random().nextInt();
        } while (Checker.checkId(id, view.getContext()));
    }

    private void init() {
        imageType = NAME_ICONS[0];

        tvError = view.findViewById(R.id.tv_error);

        btnExample = view.findViewById(R.id.btn_example);
        final Button btnAddNewView = view.findViewById(R.id.btn_add_new_view);
        tvExampleJson = view.findViewById(R.id.tv_example_json);
        edtName = view.findViewById(R.id.edt_name);
        edtPin = view.findViewById(R.id.edt_pin_controller);
        final Spinner spTypeImage = view.findViewById(R.id.sp_image_type);

        final TypingListener typingListener = new TypingListener();

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, NAME_ICONS);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spTypeImage.setAdapter(adapter);
        spTypeImage.setSelection(0);

        spTypeImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        edtName.addTextChangedListener(typingListener);
        edtPin.addTextChangedListener(typingListener);

        btnExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnExample.setActivated(!btnExample.isActivated());
                showExampleJson();
            }
        });

        btnAddNewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValues()) {
                    saveJsonForCreatingViews();
                    ((ActivityCreateNewElement) Objects.requireNonNull(getActivity())).finishActivity();

                }
            }
        });
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

        tvExampleJson.setText(stringBuilder);
    }

    private void saveJsonForCreatingViews() {
        try {
            EditorViewsJson.saveJsonForCreatingViews(getJSON(), view.getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean checkValues() {


        if (pin.trim().equals("")) {
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(R.string.pin_can_be_empty);
            return false;
        } else if (Checker.checkPin(Integer.parseInt(pin), getContext())) {
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(R.string.pin_existed);
            return false;
        }
        return true;
    }

    private JSONObject getJSON() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put(FactoryViews.TYPE_VIEW, FactoryViews.TYPE_VIEW_BUTTON);
        jsonObject.put(CreatorButton.ATR_ID, id);
        jsonObject.put(CreatorButton.ATR_TEXT, name);
        jsonObject.put(CreatorButton.ATR_PIN, Integer.parseInt(pin));
        jsonObject.put(CreatorButton.ATR_IMAGE_TYPE, imageType);

        return jsonObject;
    }

    private class TypingListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            tvError.setVisibility(View.GONE);
            name = edtName.getText().toString();
            pin = edtPin.getText().toString();

            showExampleJson();
            showExampleButton();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
