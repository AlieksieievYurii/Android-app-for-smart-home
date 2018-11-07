package com.whitedeveloper.controlhome.controller.alertdialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.prefaranse.UrlPreference;

import java.util.Objects;

public class AlertDialogSetterURL
{
    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    private ISetterURL iSetterURL;

    private EditText edtURL;
    private EditText edtUrlAdditionPath;
    private EditText edtNameParamKey;
    private EditText edtKey;

    public AlertDialogSetterURL(Context context, ISetterURL iSetterURL) {
        this.context = context;
        this.iSetterURL = iSetterURL;
        init();
    }

    private void init()
    {
        builder = new AlertDialog.Builder(context);
        View view = ((LayoutInflater) Objects.requireNonNull(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))).inflate(R.layout.layout_alert_dialog_setting_url,null);
        edtURL = view.findViewById(R.id.edt_alert_dialog_url);
        edtUrlAdditionPath = view.findViewById(R.id.edt_alert_dialog_url_addition_path);
        edtNameParamKey = view.findViewById(R.id.edt_alert_dialog_name_key_param);
        edtKey = view.findViewById(R.id.edt_alert_dialog_key);
        Button btnSet = view.findViewById(R.id.btn_alert_dialog_set);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(readPreference()) {
                    alertDialog.hide();
                    alertDialog.dismiss();
                }
                else
                    Toast.makeText(context,"Wrong URL!",Toast.LENGTH_SHORT).show();
            }
        });

        builder.setView(view);
    }

    private boolean readPreference()
    {
        String url = edtURL.getText().toString();
        String additionPath = edtUrlAdditionPath.getText().toString();
        String nameKeyParam = edtNameParamKey.getText().toString();
        String key = edtKey.getText().toString();


        if(url.replaceAll("\\s+","").equals("") || url.replaceAll("\\s+","").length() <= 10)
            return false;

        iSetterURL.setterURL(new UrlPreference(url,additionPath,nameKeyParam,key));
        return true;
    }

    public void show(UrlPreference oldUrlPreference)
    {
        try {
            edtURL.setText(oldUrlPreference.getUrl());
            edtUrlAdditionPath.setText(oldUrlPreference.getAdditionPath());
            edtNameParamKey.setText(oldUrlPreference.getNameKeyParameter());
            edtKey.setText(oldUrlPreference.getKey());
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }
}
