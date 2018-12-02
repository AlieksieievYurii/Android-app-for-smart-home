package com.whitedeveloper.controlhome.view.alertdialog;

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
    private final Context context;
    private final ISetterURL iSetterURL;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private EditText edtURL;
    private EditText edtUrlAdditionPath;
    private EditText edtUrlToHashSum;
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
        final View view = ((LayoutInflater) Objects.requireNonNull(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))).inflate(R.layout.layout_alert_dialog_setting_url,null);
        edtURL = view.findViewById(R.id.edt_alert_dialog_url);
        edtUrlToHashSum = view.findViewById(R.id.edt_alert_dialog_url_path_to_hash_sum);
        edtUrlAdditionPath = view.findViewById(R.id.edt_alert_dialog_url_addition_path);
        edtNameParamKey = view.findViewById(R.id.edt_alert_dialog_name_key_param);
        edtKey = view.findViewById(R.id.edt_alert_dialog_key);
        final Button btnSet = view.findViewById(R.id.btn_alert_dialog_set);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(readPreference()) {
                    alertDialog.hide();
                    alertDialog.dismiss();
                }
                else
                    Toast.makeText(context, R.string.wrong_url,Toast.LENGTH_SHORT).show();
            }
        });

        builder.setView(view);
    }

    private boolean readPreference()
    {
        final String url = edtURL.getText().toString();
        final String additionPath = edtUrlAdditionPath.getText().toString();
        final String pathToHashSum = edtUrlToHashSum.getText().toString();
        final String nameKeyParam = edtNameParamKey.getText().toString();
        final String key = edtKey.getText().toString();


        if(url.replaceAll("\\s+","").equals("") || url.replaceAll("\\s+","").length() <= 10)
            return false;

        iSetterURL.setterURL(new UrlPreference(url,additionPath,pathToHashSum,nameKeyParam,key));
        return true;
    }

    public void show(UrlPreference oldUrlPreference)
    {
        try {
            edtURL.setText(oldUrlPreference.getUrl());
            edtUrlAdditionPath.setText(oldUrlPreference.getAdditionPath());
            edtUrlToHashSum.setText(oldUrlPreference.getPathToHashSum());
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
