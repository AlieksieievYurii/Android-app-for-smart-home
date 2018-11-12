package com.whitedeveloper.controlhome.view.alertdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.whitedeveloper.controlhome.R;

import java.util.Objects;

public class MenuOfViewElement {

    private final Context context;
    private final int id;
    private final IEditView editView;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    public MenuOfViewElement(Context context, int id, IEditView editView) {
        this.context = context;
        this.id = id;
        this.editView = editView;

        init();
    }

    private void init()
    {

        builder = new AlertDialog.Builder(context);
        final View view = ((LayoutInflater) Objects.requireNonNull(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))).inflate(R.layout.layout_alert_dialog_menu_of_view,null);
        final Button btnEdit = view.findViewById(R.id.btn_al_edit_view);
        final Button btnRemove = view.findViewById(R.id.btn_al_remove_view);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editView.removeView(id);
                alertDialog.hide();
                alertDialog.dismiss();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editView.editView(id);
                alertDialog.hide();
                alertDialog.dismiss();
            }
        });

        builder.setView(view);
    }

    public void show()
    {
       alertDialog = builder.create();
       Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       alertDialog.show();
    }
}
