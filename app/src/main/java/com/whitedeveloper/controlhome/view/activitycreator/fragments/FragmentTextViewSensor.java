package com.whitedeveloper.controlhome.view.activitycreator.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.controlhome.factory.CheckID;
import com.whitedeveloper.controlhome.factory.FactoryViews;
import com.whitedeveloper.controlhome.factory.textview.CreatorTextView;
import com.whitedeveloper.controlhome.view.activitycreator.ActivityCreateNewElement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class FragmentTextViewSensor extends Fragment
{
    private static final String[] NAME_SENSORS = {CreatorTextView.TEMPERATURE,CreatorTextView.STATE_DAY};

    private View view;

    private EditText edtId;
    private TextView tvExample;

    private String id;
    private String typeSensor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_text_view_sensor,container,false);
        init();
        return view;
    }

    private void init()
    {
        edtId = view.findViewById(R.id.edt_id);
        Spinner spTypeSensor = view.findViewById(R.id.sp_sensor_type);
        tvExample = view.findViewById(R.id.tv_sensor_example);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, NAME_SENSORS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTypeSensor.setAdapter(adapter);
        spTypeSensor.setSelection(0);

        spTypeSensor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeSensor = NAME_SENSORS[i];
                showExampleView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        view.findViewById(R.id.btn_add_new_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                readAll();
                if(checkValues())
                {
                    saveJsonForCreatingViews();
                    ((ActivityCreateNewElement) Objects.requireNonNull(getActivity())).finishActivity();
                }

            }
        });
    }

    private void saveJsonForCreatingViews() {
           try {
               JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(view.getContext());
               jsonArray.put(getJSON());
               ControllerSharedPreference.putJsonForCreatingView(view.getContext(), jsonArray.toString());
           } catch (Exception e) {
               e.printStackTrace();
               try {
                   JSONArray jsonArray = new JSONArray();
                   jsonArray.put(getJSON());

                   ControllerSharedPreference.putJsonForCreatingView(view.getContext(), jsonArray.toString());
               } catch (JSONException e1) {
                   e1.printStackTrace();
               }
           }
       }

       private JSONObject getJSON() throws JSONException {



               JSONObject jsonObject = new JSONObject();
               jsonObject.put(FactoryViews.TYPE_VIEW,FactoryViews.TYPE_VIEW_TEXT_VIEW);
               jsonObject.put(CreatorTextView.ATR_ID,Integer.parseInt(id));
               jsonObject.put(CreatorTextView.ATR_IMAGE_TYPE,typeSensor);
               jsonObject.put(CreatorTextView.ATR_NAME_SENSOR_ARDUINO,typeSensor);

               return jsonObject;
           }
    private boolean checkValues()
          {
             if(id.trim().equals(""))
             {
                 Toast.makeText(view.getContext(),"Id can't be empty!",Toast.LENGTH_SHORT).show();
                 return false;
             }else if(!CheckID.checkId(Integer.parseInt(id),view.getContext()))
             {
                 Toast.makeText(view.getContext(),"This Id is already exists!",Toast.LENGTH_SHORT).show();
                 return false;
             }
             return true;
          }

    private void readAll()
    {
        id = edtId.getText().toString();
    }

    private void showExampleView()
    {
        tvExample.setBackgroundResource(CreatorTextView.getBackgroundResource(typeSensor));
    }
}
