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
import com.whitedeveloper.custom.PinArduino;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class FragmentButton extends Fragment {

    public static final String[] NAME_ICONS = {CreatorButton.LAMP,CreatorButton.COMPUTER,CreatorButton.FAN,CreatorButton.SOCKET};


    private Button btnExample;
    private TextView tvExampleJson;
    private EditText edtId;
    private EditText edtName;
    private EditText edtPin;

    private String id = "";
    private String name = "";
    private String pin = "null";
    private String imageType = "";

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_button,container,false);
        init();
        return view;

    }

    private void init() {
        imageType =  NAME_ICONS[0];

        btnExample = view.findViewById(R.id.btn_example);
        Button btnAddNewView = view.findViewById(R.id.btn_add_new_view);
        tvExampleJson = view.findViewById(R.id.tv_example_json);
        edtId = view.findViewById(R.id.edt_id);
        edtName = view.findViewById(R.id.edt_name);
        edtPin = view.findViewById(R.id.edt_pin_controller);
        Spinner spTypeImage = view.findViewById(R.id.sp_image_type);

        TypingListener typingListener = new TypingListener();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, NAME_ICONS);
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

        edtId.addTextChangedListener(typingListener);
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
                if(checkValues()) {
                    saveJsonForCreatingViews();
                    ((ActivityCreateNewElement) Objects.requireNonNull(getActivity())).finishActivity();

                }
            }
        });
    }

    private void showExampleButton()
    {
        btnExample.setText(name);
        btnExample.setBackgroundResource(CreatorButton.getBackgroundResource(imageType));
    }

    private void showExampleJson()
    {
        StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{").append("\n");
            stringBuilder.append("  \"").append(PinArduino.TYPE_PIN).append("\":\"").append(PinArduino.TYPE_PIN_DIGITAL).append("\",\n");
            stringBuilder.append("  \"").append(PinArduino.PIN).append("\":").append(pin).append(",\n");
            stringBuilder.append("  \"").append(PinArduino.STATUS).append("\":\"").append(btnExample.isActivated()?PinArduino.STATUS_HIGH:PinArduino.STATUS_LOW).append("\"\n");
            stringBuilder.append("}");

        tvExampleJson.setText(stringBuilder);
    }

    private void saveJsonForCreatingViews()
    {
        try {
            EditorViewsJson.saveJsonForCreatingViews(getJSON(),view.getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean checkValues()
    {
       if(id.trim().equals(""))
       {
           Toast.makeText(view.getContext(),"Id can't be empty!",Toast.LENGTH_SHORT).show();
           return false;
       }else if(Checker.checkPin(Integer.parseInt(pin),getContext()))
       {
           Toast.makeText(view.getContext(),"This pin is already exists!",Toast.LENGTH_SHORT).show();
           return false;
       }
       else if(pin.trim().equals(""))
       {
           Toast.makeText(view.getContext(),"Pin can't be empty!",Toast.LENGTH_SHORT).show();
           return false;
       }else if(Checker.checkId(Integer.parseInt(id), view.getContext()))
       {
           Toast.makeText(view.getContext(),"This Id is already exists!",Toast.LENGTH_SHORT).show();
           return false;
       }
       return true;
    }

    private JSONObject getJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FactoryViews.TYPE_VIEW,FactoryViews.TYPE_VIEW_BUTTON);
        jsonObject.put(CreatorButton.ATR_ID,Integer.parseInt(id));
        jsonObject.put(CreatorButton.ATR_TEXT,name);
        jsonObject.put(CreatorButton.ATR_PIN,Integer.parseInt(pin));
        jsonObject.put(CreatorButton.ATR_IMAGE_TYPE,imageType);

        return jsonObject;
    }

    private class TypingListener implements TextWatcher
    {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {
            id = edtId.getText().toString();
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
