package com.whitedeveloper.controlhome.view;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.Controller;
import com.whitedeveloper.controlhome.view.elementarduino.ElementArduino;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.PinArduino;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import com.whitedeveloper.custom.textview.ControllerTextView;
import com.whitedeveloper.custom.textview.intervals.Intervals;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{

    private static final ElementArduino[] elementsArduino = {
                                                        new ElementArduino(ElementArduino.BTN,R.id.btn_led_1,new PinArduino('D',22),"Led 1"),
                                                        new ElementArduino(ElementArduino.BTN,R.id.btn_led_2,new PinArduino('D',23),"Led 2"),
                                                        new ElementArduino(ElementArduino.BTN,R.id.btn_led_3,new PinArduino('D',24),"Led 3"),
                                                        new ElementArduino(ElementArduino.BTN,R.id.btn_led_4,new PinArduino('D',25),"Led 4"),
                                                        new ElementArduino(ElementArduino.BTN,R.id.btn_computer_1,new PinArduino('D',26),"Comp 1"),
                                                        new ElementArduino(ElementArduino.BTN,R.id.btn_computer_2,new PinArduino('D',27),"Comp 2"),
                                                        new ElementArduino(ElementArduino.BTN,R.id.btn_computer_3,new PinArduino('D',28),"Comp 3"),
                                                        new ElementArduino(ElementArduino.BTN,R.id.btn_computer_4,new PinArduino('D',29),"Comp 4"),
                                                        new ElementArduino(ElementArduino.SKB,R.id.sb_1,new PinArduino('A',3),null),
                                                        new ElementArduino(ElementArduino.SKB,R.id.sb_2,new PinArduino('A',4),null),
                                                        new ElementArduino(ElementArduino.SKB,R.id.sb_3,new PinArduino('A',5),null),
                                                        new ElementArduino(ElementArduino.SKB,R.id.sb_4,new PinArduino('A',6),null)};


    private static  final int[] ID_TEXT_VIEWS = {
                                    R.id.tv_temperature,
                                    R.id.tv_day_or_night};
    private static  final String[] NAME_SENSOR_ARDUINO = {"temperature","light"};

    private static final Intervals[] intervals = {
            null,
            new Intervals(700)
    };

    private ArrayList<ControllerButton> arrayListControllerButtons = new ArrayList<>();
    private ArrayList<ControllerSeekBar> arrayListControllerSeekBars = new ArrayList<>();
    private ArrayList<ControllerTextView> arrayListControllersTextView = new ArrayList<>();
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initAll();
       sendArrayListForController();
       splashScreen();
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
            default:break;
        }
        return super.onOptionsItemSelected(item);
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
        controller = new Controller(this);

        for(ElementArduino elementArduino : elementsArduino)
                    if(elementArduino.getTypeView() == ElementArduino.BTN)
                        initButton(elementArduino);
                    else if(elementArduino.getTypeView() == ElementArduino.SKB)
                        initSeekBar(elementArduino);

        initTextViewSensors();
    }
    private void initButton(ElementArduino elementArduino)
    {
        arrayListControllerButtons.add(new ControllerButton((Button) findViewById(elementArduino.getId_view()),
                                            elementArduino.getPinArduino(),
                                            elementArduino.getTextName()));
    }

    private void initSeekBar(ElementArduino elementArduino)
    {
        arrayListControllerSeekBars.add(new ControllerSeekBar((BoxedVertical)findViewById(elementArduino.getId_view()),
                                            elementArduino.getPinArduino()));
    }

    private void initTextViewSensors()
    {
        for (int i = 0; i < ID_TEXT_VIEWS.length; i++)
            arrayListControllersTextView.add(new ControllerTextView(
                    (TextView) findViewById(ID_TEXT_VIEWS[i]),
                    NAME_SENSOR_ARDUINO[i],
                    intervals[i]));
    }

    public void test(View view)
    {
        startActivity(new Intent(this,Main2Activity.class));
    }

}
