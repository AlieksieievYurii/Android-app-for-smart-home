package com.whitedeveloper.controlhome.view;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.Controller;
import com.whitedeveloper.controlhome.controller.interfaces.ISettuperTextViewSensors;
import com.whitedeveloper.controlhome.controller.interfaces.ISetuperArrayListButtons;
import com.whitedeveloper.controlhome.controller.interfaces.ISetuperArrayListSeekBars;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.PinArduino;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import com.whitedeveloper.custom.textview.ControllerTextView;
import com.whitedeveloper.custom.textview.intervals.Intervals;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{

    private static final int[] ID_BUTTONS = {R.id.btn_led_1,
                                    R.id.btn_led_2,
                                    R.id.btn_led_3,
                                    R.id.btn_led_4,
                                    R.id.btn_computer_1,
                                    R.id.btn_computer_2,
                                    R.id.btn_computer_3,
                                    R.id.btn_computer_4};

    private static final PinArduino[] PIN_ARDUINO_BUTTONS = {
                                    new PinArduino('D',30),
                                    new PinArduino('D',29),
                                    new PinArduino('D',28),
                                    new PinArduino('D',27),
                                    new PinArduino('D',26),
                                    new PinArduino('D',25),
                                    new PinArduino('D',24),
                                    new PinArduino('D',23),
                                    new PinArduino('D',22)};

    private final int[] ID_SEEK_BARS = {R.id.sb_1,
                                    R.id.sb_2,
                                    R.id.sb_3,
                                    R.id.sb_4};

    private static final PinArduino[] PIN_ARDUINO_SEEK_BAR = {new PinArduino('A',2),
                                    new PinArduino('A',3),
                                    new PinArduino('A',4),
                                    new PinArduino('A',5)};

    private static  final int[] ID_TEXT_VIEWS = {
                                    R.id.tv_temperature,
                                    R.id.tv_day_or_night};
    private static  final String[] NAME_SENSOR_ARDUINO = {"light","TEST_PARAM"};

    private static final Intervals[] interwals = {
            null,
            new Intervals(700)
    };

    private ArrayList<ControllerButton> arrayListControllerButtons = new ArrayList<>();
    private ArrayList<ControllerSeekBar> arrayListControllerSeekBars = new ArrayList<>();
    private ArrayList<ControllerTextView> arrayListControllersTextView = new ArrayList<>();
    private ISetuperArrayListButtons setuperArrayListButtons;
    private ISetuperArrayListSeekBars setuperArrayListSeekBars;
    private ISettuperTextViewSensors settuperTextViewSensors;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initAll();
       sendArrayListForController();
       splashScreen();
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
        setuperArrayListButtons.iSettuperArrayListButtons(arrayListControllerButtons);
        setuperArrayListSeekBars.iSettuperArrayListSeekBars(arrayListControllerSeekBars);
        settuperTextViewSensors.setArrayListTextViewSensors(arrayListControllersTextView);

    }

    private void initAll()
    {
        initButtons();
        initSeekBars();
        initTextViewSensors();

        Controller controller = new Controller();
        setuperArrayListButtons = controller;
        setuperArrayListSeekBars = controller;
        settuperTextViewSensors = controller;
    }
    private void initButtons()
    {
        for (int i = 0; i < ID_BUTTONS.length; i++)
            arrayListControllerButtons.add(new ControllerButton((Button) findViewById(ID_BUTTONS[i]),PIN_ARDUINO_BUTTONS[i],"Home"));
    }

    private void initSeekBars()
    {
        for(int i = 0; i < ID_SEEK_BARS.length; i++)
            arrayListControllerSeekBars.add(new ControllerSeekBar((BoxedVertical)findViewById(ID_SEEK_BARS[i]),PIN_ARDUINO_SEEK_BAR[i]));
    }

    private void initTextViewSensors()
    {
        for (int i = 0; i < ID_TEXT_VIEWS.length; i++)
            arrayListControllersTextView.add(new ControllerTextView(
                    (TextView) findViewById(ID_TEXT_VIEWS[i]),
                    NAME_SENSOR_ARDUINO[i],
                    interwals[i]));
    }

}
