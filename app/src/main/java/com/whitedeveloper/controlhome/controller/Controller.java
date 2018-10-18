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
    private DataFromServer dataFromServer;
    private ArrayList<ControllerButton> controllerButtons;
    String likeData = "[{\"T\":\"D\",\"P\":3,\"S\":\"L\"},{\"T\":\"D\",\"P\":4,\"S\":\"L\"},{\"T\":\"D\",\"P\":5,\"S\":\"L\"},{\"T\":\"D\",\"P\":6,\"S\":\"L\"},{\"T\":\"D\",\"P\":7,\"S\":\"H\"},{\"T\":\"D\",\"P\":8,\"S\":\"L\"},{\"T\":\"D\",\"P\":9,\"S\":\"H\"},{\"T\":\"D\",\"P\":10,\"S\":\"L\"}]";

    public Controller()
    {
        dataFromServer  = new DataFromServer(
                "http://192.168.0.106:8080/actions",
                "JbhT692Hy2",
                this);
        dataFromServer.readDataFromServer();
    }

    private void setButtonsByActionsFromServer()
    {
        try {
            SetterStatusViewFRomServer.setButtonsStatuc(this.controllerButtons,likeData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendToServer(String json)
        {
            Log.i("TAG",json);
            dataFromServer.writeDataToServer(json);
        }

    @Override
    public void iSettuperArrayListButtons(ArrayList<ControllerButton> controllerButtons)
    {
        new ImplementationButtons(controllerButtons,this);
        this.controllerButtons = controllerButtons;
    }


    @Override
    public void updateActiviti(String data) {
        setButtonsByActionsFromServer();
    }

    @Override
    public void onClickButton() {
        sendToServer(CreatorJsonByControllerButton.getJsonActions(this.controllerButtons));
    }
}
