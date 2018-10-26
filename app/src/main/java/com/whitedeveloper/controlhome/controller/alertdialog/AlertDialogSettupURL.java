package com.whitedeveloper.controlhome.controller.alertdialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.whitedeveloper.controlhome.R;

public class AlertDialogSettupURL
{
    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    private EditText edtURL;
    private EditText edtNameParamKey;
    private EditText edtKey;
    private Button btnSet;

    public AlertDialogSettupURL(Context context) {
        this.context = context;
        init();
    }

    private void init()
    {
        builder = new AlertDialog.Builder(context);
        View view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_alert_dialog_setting_url,null);
        edtURL = view.findViewById(R.id.edt_alert_dialog_url);
        edtNameParamKey = view.findViewById(R.id.edt_alert_dialog_name_key_param);
        edtKey = view.findViewById(R.id.edt_alert_dialog_key);
        btnSet = view.findViewById(R.id.btn_alert_dialog_set);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("URL--",edtURL.getText().toString());
                Log.i("KEY--",edtKey.getText().toString());
                alertDialog.hide();
            }
        });

        builder.setView(view);
    }

    public void show()
    {
        alertDialog = builder.create();
        alertDialog.show();
    }
}
