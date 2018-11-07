package com.whitedeveloper.controlhome.view.activitycreator.editorviews;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.controlhome.controller.prefaranse.EditorViewsJson;
import com.whitedeveloper.controlhome.factory.FactoryViews;
import com.whitedeveloper.controlhome.factory.seekbar.CreatorSeekBar;
import com.whitedeveloper.controlhome.factory.textview.CreatorTextView;
import com.whitedeveloper.controlhome.view.activitycreator.fragments.FragmentTextViewSensor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


class EditorTextViewSensor
{
    private AppCompatActivity appCompatActivity;

    private TextView tvExample;
    private Spinner spinnerImageType;

    private int id;
    private String imageType;

    EditorTextViewSensor(AppCompatActivity appCompatActivity, int id) {
        this.appCompatActivity = appCompatActivity;
        this.id = id;

        appCompatActivity.setContentView(R.layout.fragment_text_view_sensor);

        init();
        setAllOldFields();
    }

    private void setAllOldFields()
    {
        try {
            JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(appCompatActivity.getBaseContext());
            for(int i = 0; i < jsonArray.length(); i++)
            {
                if(jsonArray.getJSONObject(i).getInt(CreatorSeekBar.ATR_ID) == id)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    imageType = jsonObject.getString(CreatorTextView.ATR_IMAGE_TYPE);
                    spinnerImageType.setSelection(getIndexOfNAME_TYPESbyName(imageType));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showExampleTextView();
    }

    private int getIndexOfNAME_TYPESbyName(String name)
    {
        for(int i = 0; i < FragmentTextViewSensor.NAME_SENSORS.length; i++)
            if(FragmentTextViewSensor.NAME_SENSORS[i].equals(name))
                return i;
        return 0;
    }

    private void init()
    {
        final EditText edtId = appCompatActivity.findViewById(R.id.edt_id);
        edtId.setText(String.valueOf(id));
        edtId.setEnabled(false);

        spinnerImageType = appCompatActivity.findViewById(R.id.sp_sensor_type);
        tvExample = appCompatActivity.findViewById(R.id.tv_sensor_example);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(appCompatActivity.getBaseContext(), android.R.layout.simple_spinner_item, FragmentTextViewSensor.NAME_SENSORS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerImageType.setAdapter(adapter);

        spinnerImageType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imageType = FragmentTextViewSensor.NAME_SENSORS[i];
                showExampleTextView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        appCompatActivity.findViewById(R.id.btn_add_new_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                saveJsonForCreatingViews();
                appCompatActivity.setResult(Activity.RESULT_OK);
                appCompatActivity.finish();
            }
        });
    }

    private void saveJsonForCreatingViews() {
        try {
            EditorViewsJson.saveChangedJsonForCreatingView(getJSON(),appCompatActivity.getBaseContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FactoryViews.TYPE_VIEW,FactoryViews.TYPE_VIEW_TEXT_VIEW);
        jsonObject.put(CreatorTextView.ATR_ID,id);
        jsonObject.put(CreatorTextView.ATR_IMAGE_TYPE,imageType);
        jsonObject.put(CreatorTextView.ATR_NAME_SENSOR_ARDUINO,imageType);

        return jsonObject;
    }

    private void showExampleTextView()
    {
        tvExample.setBackgroundResource(CreatorTextView.getBackgroundResource(imageType));
    }
}
