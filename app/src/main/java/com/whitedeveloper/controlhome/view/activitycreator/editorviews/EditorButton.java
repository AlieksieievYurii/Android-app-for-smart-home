package com.whitedeveloper.controlhome.view.activitycreator.editorviews;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.controlhome.factory.FactoryViews;
import com.whitedeveloper.controlhome.factory.button.CreatorButton;
import com.whitedeveloper.custom.PinArduino;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.whitedeveloper.controlhome.view.activitycreator.fragments.FragmentButton.NAME_ICONS;

public class EditorButton
{
    private AppCompatActivity appCompatActivity;

    private int id;
    private EditText edtName;
    private EditText edtPinController;
    private Spinner spImageType;

    private Button btnApply;
    private Button btnExample;

    private TextView tvExampleJSON;

    private String name;
    private String pin;
    private String imageType;

    public EditorButton(AppCompatActivity appCompatActivity, int id)
    {
        this.id = id;
        this.appCompatActivity = appCompatActivity;
        appCompatActivity.setContentView(R.layout.fragment_button);

        init();
        setAllOldFields();
    }

    private void init()
    {

        EditText edtId = appCompatActivity.findViewById(R.id.edt_id);
        edtId.setText(String.valueOf(id));
        edtId.setEnabled(false);

        edtName = appCompatActivity.findViewById(R.id.edt_name);
        edtPinController = appCompatActivity.findViewById(R.id.edt_pin_controller);
        spImageType = appCompatActivity.findViewById(R.id.sp_image_type);
        tvExampleJSON = appCompatActivity.findViewById(R.id.tv_example_json);

        btnExample = appCompatActivity.findViewById(R.id.btn_example);
        btnExample.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       btnExample.setActivated(!btnExample.isActivated());
                       showExampleJson();
                   }
               });

        TypingListener typingListener = new TypingListener();
        edtName.addTextChangedListener(typingListener);
        edtPinController.addTextChangedListener(typingListener);

        btnApply = appCompatActivity.findViewById(R.id.btn_add_new_view);
        btnApply.setText(R.string.text_editor_views_btn_apply);
        btnApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkValues()) {
                            //TODO Implement swapping element in array(I mean save)...okey never mind
                            appCompatActivity.setResult(Activity.RESULT_OK);
                            appCompatActivity.finish();
                        }
                    }
                });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(appCompatActivity.getBaseContext(), android.R.layout.simple_spinner_item, NAME_ICONS);
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

        tvExampleJSON.setText(stringBuilder);
    }

    private boolean checkValues()
    {
        if(pin.trim().equals(""))
       {
           Toast.makeText(appCompatActivity.getBaseContext(),"Pin can't be empty!",Toast.LENGTH_SHORT).show();
           return false;
       }

       return true;
    }

    private JSONObject getJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FactoryViews.TYPE_VIEW,FactoryViews.TYPE_VIEW_BUTTON);
        jsonObject.put(CreatorButton.ATR_ID,id );
        jsonObject.put(CreatorButton.ATR_TEXT,name);
        jsonObject.put(CreatorButton.ATR_PIN,Integer.parseInt(pin));
        jsonObject.put(CreatorButton.ATR_IMAGE_TYPE,imageType);

        return jsonObject;
    }

    private void setAllOldFields()
    {
        try {
            JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(appCompatActivity.getBaseContext());
            for(int i = 0; i < jsonArray.length(); i++)
            {
                if(jsonArray.getJSONObject(i).getInt(CreatorButton.ATR_ID) == id)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    imageType = jsonObject.getString(CreatorButton.ATR_IMAGE_TYPE);
                    setImageButtonExample(imageType);

                    edtName.setText(jsonObject.getString(CreatorButton.ATR_TEXT));
                    edtPinController.setText(jsonObject.getString(CreatorButton.ATR_PIN));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setImageButtonExample(String nameImageType)
    {
        for(int i = 0; i < NAME_ICONS.length; i++)
        {
            if(NAME_ICONS[i].equals(nameImageType))
                spImageType.setSelection(i);
        }
    }

    private class TypingListener implements TextWatcher
    {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {
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
