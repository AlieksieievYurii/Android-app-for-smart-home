package com.whitedeveloper.controlhome.view.activitycreator.editorviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.factory.FactoryViews;

public class ActivityEditView extends AppCompatActivity
{

    private static final String ID =  "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);

        int id = getIntent().getIntExtra(ID,0);

        switch (getTypeView(id))
        {
            case FactoryViews.TYPE_VIEW_BUTTON:
                new EditorButton(this,id);
                break;
            case FactoryViews.TYPE_VIEW_SEEK_BAR:
                Log.i("TEST","SEEK_BAR");
                break;
            case FactoryViews.TYPE_VIEW_TEXT_VIEW:
                Log.i("TEST","SENSOR");
                break;
            default:
                Log.i("ERROR","ERROR");
                break;
        }
    }

    private String getTypeView(int id)
    {
        try {
           return FinderTypeElement.findTypeElementById(id,this);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
