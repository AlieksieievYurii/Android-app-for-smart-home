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
import com.whitedeveloper.controlhome.controller.prefaranse.EditorViewsJson;
import com.whitedeveloper.controlhome.factory.Checker;
import com.whitedeveloper.controlhome.factory.FactoryViews;
import com.whitedeveloper.controlhome.factory.textview.CreatorTextView;
import com.whitedeveloper.controlhome.view.activitycreator.ActivityCreateNewElement;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;

public class FragmentTextViewSensor extends Fragment {
    public static final String[] NAME_SENSORS = {CreatorTextView.TEMPERATURE, CreatorTextView.STATE_DAY};

    private View view;
    private TextView tvExample;
    private String typeSensor;

    private int id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_text_view_sensor, container, false);
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
        tvExample = view.findViewById(R.id.tv_sensor_example);
        final Spinner spTypeSensor = view.findViewById(R.id.sp_sensor_type);

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, NAME_SENSORS);
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
            public void onClick(View view) {
                saveJsonForCreatingViews();
                ((ActivityCreateNewElement) Objects.requireNonNull(getActivity())).finishActivity();
            }
        });
    }

    private void saveJsonForCreatingViews() {
        try {
            EditorViewsJson.saveJsonForCreatingViews(getJSON(), view.getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FactoryViews.TYPE_VIEW, FactoryViews.TYPE_VIEW_TEXT_VIEW);
        jsonObject.put(CreatorTextView.ATR_ID, id);
        jsonObject.put(CreatorTextView.ATR_IMAGE_TYPE, typeSensor);
        jsonObject.put(CreatorTextView.ATR_NAME_SENSOR_ARDUINO, typeSensor);

        return jsonObject;
    }


    private void showExampleView() {
        tvExample.setBackgroundResource(CreatorTextView.getBackgroundResource(typeSensor));
    }
}
