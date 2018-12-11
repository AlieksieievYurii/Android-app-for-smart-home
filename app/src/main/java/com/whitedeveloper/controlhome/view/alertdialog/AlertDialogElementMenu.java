package com.whitedeveloper.controlhome.view.alertdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import com.whitedeveloper.controlhome.R;

import java.util.Objects;

public class AlertDialogElementMenu extends AlertDialog {

    public interface CallBackDialogElementMenu {
        void removeView(int id);
        void editView(int id);
    }

    private final int id;
    private final CallBackDialogElementMenu callBack;

    public AlertDialogElementMenu(@NonNull Context context, int id, CallBackDialogElementMenu callBack ) {
        super(context);
        this.id = id;
        this.callBack = callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alert_dialog_element_menu);
        init();
    }

    private void init() {
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final Button btnEdit = findViewById(R.id.btn_al_edit_view);
        final Button btnRemove = findViewById(R.id.btn_al_remove_view);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.removeView(id);
                hide();
                dismiss();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.editView(id);
                hide();
                dismiss();
            }
        });
    }
}
