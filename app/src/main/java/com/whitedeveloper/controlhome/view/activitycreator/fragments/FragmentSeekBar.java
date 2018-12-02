package com.whitedeveloper.controlhome.view.activitycreator.fragments;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.prefaranse.EditorViewsJson;
import com.whitedeveloper.controlhome.factory.Checker;
import com.whitedeveloper.controlhome.view.activitycreator.ActivityCreateNewElement;
import com.whitedeveloper.custom.PinOfTCOD;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;

import static com.whitedeveloper.TagKeys.*;

public class FragmentSeekBar extends Fragment {

    private EditText edtName;
    private EditText edtPin;
    private TextView tvExampleJson;
    private TextView tvTextOfSeekBar;
    private TextView tvError;

    private View view;

    private int id;
    private String name = "";
    private String pin = "";
    private int value = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_seek_bar, container, false);
        init();
        showExampleJson();
        setRandomId();
        return view;
    }

    private void setRandomId() {
        do {
            id = new Random().nextInt();
        } while (Checker.checkId(id, view.getContext()));
    }

    private void init() {
        edtName = view.findViewById(R.id.edt_name);
        edtPin = view.findViewById(R.id.edt_pin_controller);
        tvExampleJson = view.findViewById(R.id.tv_example_json);
        tvTextOfSeekBar = view.findViewById(R.id.tv_text_seek_bar);
        tvError = view.findViewById(R.id.tv_error);


        final TypingListener typingListener = new TypingListener();

        edtName.addTextChangedListener(typingListener);
        edtPin.addTextChangedListener(typingListener);

        ((BoxedVertical) view.findViewById(R.id.bv_example)).setOnBoxedPointsChangeListener(new BoxedVertical.OnValuesChangeListener() {
            @Override
            public void onPointsChanged(BoxedVertical boxedVertical, int i) {

            }

            @Override
            public void onStartTrackingTouch(BoxedVertical boxedVertical) {

            }

            @Override
            public void onStopTrackingTouch(BoxedVertical boxedVertical) {
                value = boxedVertical.getValue();
                showExampleJson();
                showExampleSeekBar();
            }
        });

        view.findViewById(R.id.btn_add_new_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValues()) {
                    saveJsonForCreatingViews();
                    ((ActivityCreateNewElement) Objects.requireNonNull(getActivity())).finishActivity();
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
        } else if (Checker.checkPin(Integer.parseInt(pin), getContext())) {
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(R.string.pin_existed);
            return false;
        } else
            return true;
    }

    private void saveJsonForCreatingViews() {
        try {
            EditorViewsJson.saveJsonForCreatingViews(getJSON(), view.getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJSON() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put(TYPE_VIEW, TYPE_VIEW_SEEK_BAR);
        jsonObject.put(ATR_ID, id);
        jsonObject.put(ATR_NAME, name);
        jsonObject.put(ATR_PIN, Integer.parseInt(pin));

        return jsonObject;
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
            pin = edtPin.getText().toString();

            showExampleJson();
            showExampleSeekBar();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
