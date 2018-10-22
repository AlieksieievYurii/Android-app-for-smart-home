package com.whitedeveloper.controlhome.controller;

import android.util.Log;
import com.whitedeveloper.controlhome.controller.interfaces.ISetuperArrayListButtons;
import com.whitedeveloper.controlhome.controller.interfaces.UpDateActiviti;
import com.whitedeveloper.controlhome.controller.json.CreatorJsonByControllerButton;
import com.whitedeveloper.controlhome.model.DataFromServer;
import com.whitedeveloper.controlhome.model.http.interfeice.IonClickButton;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.buttons.ImplementationButtons;
import org.json.JSONException;

import java.util.ArrayList;

public class Controller implements ISetuperArrayListButtons, UpDateActiviti, IonClickButton
{
    private static final String SERVLET_ACTION_BY_DEVICES = "actions-by-device";
    private static final String HOST = "http://192.168.0.106:8080/";
    private static final String KEY_TO_HOST = "12345678";

    private DataFromServer dataFromServer;
    private ArrayList<ControllerButton> controllerButtons;

    public Controller()
    {
        dataFromServer  = new DataFromServer(
                HOST,
                KEY_TO_HOST,
                this);

        readingFromServer();
    }

    private void setButtonsByActionsFromServer(String data)
    {
        try {
            SetterStatusViewFRomServer.setButtonsStatus(this.controllerButtons,data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void readingFromServer()
    {
        dataFromServer.readDataFromServer(SERVLET_ACTION_BY_DEVICES);
    }

    private void sendToServer(String json)
        {
            Log.i("TAG",json);
            dataFromServer.writeDataToServer(SERVLET_ACTION_BY_DEVICES,json);
        }

    @Override
    public void iSettuperArrayListButtons(ArrayList<ControllerButton> controllerButtons)
    {
        new ImplementationButtons(controllerButtons,this);
        this.controllerButtons = controllerButtons;
    }


    @Override
    public void updateActiviti(String data) {
        setButtonsByActionsFromServer(data);
    }

    @Override
    public void onClickButton() {
        sendToServer(CreatorJsonByControllerButton.getJsonActions(this.controllerButtons));
        readingFromServer();
    }
}
