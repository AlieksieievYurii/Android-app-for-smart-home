package com.whitedeveloper.controlhome.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.Controller;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.controlhome.factory.FactoryViews;
import com.whitedeveloper.controlhome.factory.IcreateView;
import com.whitedeveloper.controlhome.view.activitycreator.ActivityCreateNewElement;
import com.whitedeveloper.custom.IrefreshActivity;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import com.whitedeveloper.custom.textview.ControllerTextView;
import org.json.JSONArray;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IcreateView, IrefreshActivity
{

    public static final int CODE_REQUEST_ACTIVITY_CREATE_NEW_VIEW = 1;

    private ArrayList<ControllerButton> arrayListControllerButtons = new ArrayList<>();
    private ArrayList<ControllerSeekBar> arrayListControllerSeekBars = new ArrayList<>();
    private ArrayList<ControllerTextView> arrayListControllersTextView = new ArrayList<>();
    private Controller controller;
    private FactoryViews factoryViews;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initAll();
       createViews();
       sendArrayListForController();
       splashScreen();
       controller.readingFromServer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.activity_main_item_setting:
                controller.setPreference();
                break;
            case R.id.activity_main_item_add_new_view:
                startActivityForResult(new Intent(MainActivity.this, ActivityCreateNewElement.class),CODE_REQUEST_ACTIVITY_CREATE_NEW_VIEW);
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_REQUEST_ACTIVITY_CREATE_NEW_VIEW && resultCode == Activity.RESULT_OK)
            refresh();
        Log.i("OK","THE BEST KEK "+ requestCode + "  " + resultCode);
    }

    private void splashScreen()
    {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                start();
            }
        };
        handler.postDelayed(runnable,2000);
    }

    private void start()
    {
        (findViewById(R.id.img_logo)).setVisibility(View.GONE);
        (findViewById(R.id.lnl_body)).setVisibility(View.VISIBLE);
    }


    private void sendArrayListForController()
    {
       controller.setArrayListButtons(arrayListControllerButtons);
       controller.setArrayListSeekBars(arrayListControllerSeekBars);
       controller.setArrayListTextViewSensors(arrayListControllersTextView);
    }

    private void initAll()
    {
        controller = new Controller(this,this);
        factoryViews = new FactoryViews(this,this);
        gridLayout = findViewById(R.id.main_grid_layout);
    }

    private void createViews() {
        try {
            JSONArray jsonArray = ControllerSharedPreference.getJsonForCreatingView(this);
            Log.i("JSON_VIEWS::",jsonArray.toString());
            factoryViews.createViews(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createButton(ControllerButton controllerButton)
    {
        arrayListControllerButtons.add(controllerButton);
        gridLayout.addView(controllerButton.getButton());

    }

    @Override
    public void createSeekBar(ControllerSeekBar controllerSeekBar)
    {
        arrayListControllerSeekBars.add(controllerSeekBar);
        gridLayout.addView(controllerSeekBar.getLinearLayout());
    }

    @Override
    public void createTextView(ControllerTextView controllerTextView) {
        arrayListControllersTextView.add(controllerTextView);
        gridLayout.addView(controllerTextView.getTextViewSensor());
    }

    @Override
    public void refresh() {
        arrayListControllerButtons.clear();
        arrayListControllerSeekBars.clear();
        arrayListControllersTextView.clear();
        gridLayout.removeAllViews();
        createViews();
        sendArrayListForController();
    }
}
