package com.whitedeveloper.controlhome.view.alertdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.prefaranse.UrlPreference;

import java.util.Objects;

public class AlertDialogSettingsUrl extends Dialog
{
    public interface CallBackAlertDialogSettingsUrl
    {
        void commitNewUrl(UrlPreference urlPreference);
    }

    private final CallBackAlertDialogSettingsUrl callBack;
    private EditText edtURL;
    private EditText edtUrlAdditionPath;
    private EditText edtUrlToHashSum;
    private EditText edtNameParamKey;
    private EditText edtKey;

    public AlertDialogSettingsUrl(@NonNull Context context,CallBackAlertDialogSettingsUrl callBack) {
        super(context);
        this.callBack = callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alert_dialog_setting_url);
        init();
    }

    private void init()
    {
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        edtURL = findViewById(R.id.edt_alert_dialog_url);
        edtUrlToHashSum = findViewById(R.id.edt_alert_dialog_url_path_to_hash_sum);
        edtUrlAdditionPath = findViewById(R.id.edt_alert_dialog_url_addition_path);
        edtNameParamKey = findViewById(R.id.edt_alert_dialog_name_key_param);
        edtKey = findViewById(R.id.edt_alert_dialog_key);
        final Button btnSet = findViewById(R.id.btn_alert_dialog_set);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(readPreference()) {
                    hide();
                    dismiss();
                }
                else
                    Toast.makeText(getContext(), R.string.wrong_url,Toast.LENGTH_SHORT).show();
            }
        });
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

        callBack.commitNewUrl(new UrlPreference(url,additionPath,pathToHashSum,nameKeyParam,key));
        return true;
    }

    public void show(UrlPreference oldUrlPreference)
    {
        show();

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
    }
}
