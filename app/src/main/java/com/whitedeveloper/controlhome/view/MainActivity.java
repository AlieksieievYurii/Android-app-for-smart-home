package com.whitedeveloper.controlhome.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.controller.Controller;
import com.whitedeveloper.controlhome.controller.interfaces.ISetuperArrayListButtons;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.PinArduino;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    private final int[] ID_BUTTONS = {R.id.btn_led_1,
                                    R.id.btn_led_2,
                                    R.id.btn_led_3,
                                    R.id.btn_led_4,
                                    R.id.btn_computer_1,
                                    R.id.btn_computer_2,
                                    R.id.btn_computer_3,
                                    R.id.btn_computer_4};

    private final int[] ID_SEEK_BARS = {R.id.sb_1};

    private final PinArduino[] PIN_ARDUINO = {new PinArduino('D',3),
                                    new PinArduino('D',4),
                                    new PinArduino('D',5),
                                    new PinArduino('D',6),
                                    new PinArduino('D',7),
                                    new PinArduino('D',8),
                                    new PinArduino('D',9),
                                    new PinArduino('D',10),
                                    new PinArduino('D',11)};

    private ArrayList<ControllerButton> arrayListControllerButtons = new ArrayList<>();
    private ISetuperArrayListButtons setuperArrayListButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       init();
       sendArrayListForController();
    }


    private void sendArrayListForController()
    {
        setuperArrayListButtons.iSettuperArrayListButtons(arrayListControllerButtons);
    }

    private void init()
    {
        for (int i = 0; i < ID_BUTTONS.length; i++)
            arrayListControllerButtons.add(new ControllerButton((Button) findViewById(ID_BUTTONS[i]),PIN_ARDUINO[i],"Home"));

        setuperArrayListButtons = new Controller();
    }

}
