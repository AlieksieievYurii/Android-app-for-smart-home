package com.whitedeveloper.controlhome.view.activitycreator.editorviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.whitedeveloper.controlhome.R;

import static com.whitedeveloper.TagKeys.*;

public class ActivityEditView extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);

        final int id = getIntent().getIntExtra(ATR_ID,0);

        switch (getTypeView(id))
        {
            case TYPE_VIEW_BUTTON:
                new EditorButton(this,id);
                break;
            case TYPE_VIEW_SEEK_BAR:
                new EditorSeekBar(this,id);
                break;
            case TYPE_VIEW_TEXT_VIEW:
               new EditorTextViewSensor(this,id);
                break;
            default:
                setResult(RESULT_CANCELED);
                finish();
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
