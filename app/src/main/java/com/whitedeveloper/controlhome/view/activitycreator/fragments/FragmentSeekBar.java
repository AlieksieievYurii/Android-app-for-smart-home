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
import android.widget.Toast;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.prefaranse.EditorViewsJson;
import com.whitedeveloper.controlhome.factory.Checker;
import com.whitedeveloper.controlhome.factory.FactoryViews;
import com.whitedeveloper.controlhome.factory.seekbar.CreatorSeekBar;
import com.whitedeveloper.controlhome.view.activitycreator.ActivityCreateNewElement;
import com.whitedeveloper.custom.PinArduino;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class FragmentSeekBar extends Fragment {

    private EditText edtId;
    private EditText edtName;
    private EditText edtPin;
    private TextView tvExampleJson;
    private TextView tvTextOfSeekBar;

    private View view;

    private String id = "";
    private String name = "";
    private String pin = "null";
    private int value = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_seek_bar,container,false);
        init();
        showExampleJson();
        return view;
    }

    private void init()
    {
        edtId = view.findViewById(R.id.edt_id);
        edtName = view.findViewById(R.id.edt_name);
        edtPin = view.findViewById(R.id.edt_pin_controller);
        tvExampleJson = view.findViewById(R.id.tv_example_json);
        tvTextOfSeekBar = view.findViewById(R.id.tv_text_seek_bar);

        final TypingListener typingListener = new TypingListener();

        edtId.addTextChangedListener(typingListener);
        edtName.addTextChangedListener(typingListener);
        edtPin.addTextChangedListener(typingListener);

        ((BoxedVertical)view.findViewById(R.id.bv_example)).setOnBoxedPointsChangeListener(new BoxedVertical.OnValuesChangeListener() {
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
                if(checkValues())
                {
                    saveJsonForCreatingViews();
                    ((ActivityCreateNewElement) Objects.requireNonNull(getActivity())).finishActivity();
                }
            }
        });
    }

    private void showExampleSeekBar()
    {
        tvTextOfSeekBar.setText(name);
    }

    private void showExampleJson()
    {
       final StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("{").append("\n");
                    stringBuilder.append("  \"").append(PinArduino.TYPE_PIN).append("\":\"").append(PinArduino.TYPE_PIN_DIGITAL_ANALOG).append("\",\n");
                    stringBuilder.append("  \"").append(PinArduino.PIN).append("\":").append(pin).append(",\n");
                    stringBuilder.append("  \"").append(PinArduino.STATUS).append("\":").append(value).append("\n");
                    stringBuilder.append("}");
        tvExampleJson.setText(stringBuilder);
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
          }else if(pin.trim().equals(""))
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

    private void saveJsonForCreatingViews() {
        try {
            EditorViewsJson.saveJsonForCreatingViews(getJSON(),view.getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJSON() throws JSONException {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put(FactoryViews.TYPE_VIEW,FactoryViews.TYPE_VIEW_SEEK_BAR);
            jsonObject.put(CreatorSeekBar.ATR_ID,Integer.parseInt(id));
            jsonObject.put(CreatorSeekBar.ATR_NAME,name);
            jsonObject.put(CreatorSeekBar.ATR_PIN,Integer.parseInt(pin));

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
                showExampleSeekBar();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        }
}
