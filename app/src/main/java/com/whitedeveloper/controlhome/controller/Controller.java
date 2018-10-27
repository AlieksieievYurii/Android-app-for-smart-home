package com.whitedeveloper.controlhome.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.whitedeveloper.controlhome.controller.alertdialog.AlertDialogSetterURL;
import com.whitedeveloper.controlhome.controller.alertdialog.ISetterURL;
import com.whitedeveloper.controlhome.controller.interfaces.*;
import com.whitedeveloper.controlhome.controller.json.CreatorJsonByControllerButton;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerUrlSharedPreference;
import com.whitedeveloper.controlhome.controller.prefaranse.UrlPreference;
import com.whitedeveloper.controlhome.model.DataFromServer;
import com.whitedeveloper.controlhome.model.cheduletimer.ItimeUpDate;
import com.whitedeveloper.controlhome.model.cheduletimer.ScheduleTimeUpDate;
import com.whitedeveloper.custom.buttons.IonClickButton;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.buttons.ImplementationButtons;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import com.whitedeveloper.custom.seekbar.ImplementationSeekBars;
import com.whitedeveloper.custom.seekbar.IonDoSeekBar;
import com.whitedeveloper.custom.textview.ControllerTextView;
import org.json.JSONException;
import java.util.ArrayList;

public class Controller implements
        ISetterArrayListButtons,
        ISetterArrayListSeekBars,
        ISetterTextViewSensors,
                        UpDateActivity,
                        IonClickButton,
                        IonDoSeekBar,
                        ICallSetting,
                        ISetterURL,
                        ItimeUpDate

{
    private static final String SERVLET_ACTION_BY_DEVICES = "actions-by-device";

    private DataFromServer dataFromServer;
    private ScheduleTimeUpDate scheduleTimeUpDate;
    private VibrationButton vibrationButton;
    private ArrayList<ControllerButton> controllerButtons;
    private ArrayList<ControllerSeekBar> controllerSeekBars;
    private ArrayList<ControllerTextView> controllerTextViews;

    private Context context;

    public  Controller(Context context)
    {
        this.context = context;
        this.dataFromServer  = new DataFromServer(
                getURLpreferance(),
                this);
        this.vibrationButton = new VibrationButton(context);
        this.vibrationButton.setActivated(true);
        this.scheduleTimeUpDate = new ScheduleTimeUpDate(this);
        this.scheduleTimeUpDate.run();
        readingFromServer();
    }

    private void runWithServer()
    {
        sendToServer(CreatorJsonByControllerButton.getReadyJsonActionsForArduino(
                this.controllerButtons,
                this.controllerSeekBars));
                readingFromServer();
    }

    private void setViewsByActionsFromServer(String data)
    {
        try {
            SetterStatusViewFRomServer.setButtonsStatus(this.controllerButtons,data);
            SetterStatusViewFRomServer.setSeekBarsStatus(this.controllerSeekBars,data);
            SetterStatusViewFRomServer.setTextViewSensors(this.controllerTextViews,data);
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
        Log.i("URL_ADDRESS::", getURLpreferance().getFullUrl(SERVLET_ACTION_BY_DEVICES));
        Log.i("DATA_FOR_SERVER:::",json);
        dataFromServer.writeDataToServer(SERVLET_ACTION_BY_DEVICES,json);
    }

    private UrlPreference getURLpreferance()
    {
        return ControllerUrlSharedPreference.getUrlPreference(context);
    }

    @Override
    public void iSetterArrayListButtons(ArrayList<ControllerButton> controllerButtons) {
        new ImplementationButtons(controllerButtons,this);
        this.controllerButtons = controllerButtons;
    }

    @Override
    public void iSetterArrayListSeekBars(ArrayList<ControllerSeekBar> controllerSeekBarArrayList) {
            new ImplementationSeekBars(controllerSeekBarArrayList,this);
            this.controllerSeekBars = controllerSeekBarArrayList;
    }


    @Override
    public void onClickButton() {

        runWithServer();
        vibrationButton.pressEffect();
    }


    @Override
    public void onDoSeekBar() {

        runWithServer();
        vibrationButton.pressEffect();
    }

    @Override
    public void updateActivity(String data, int codeResponse) {
          if(data != null) {
              setViewsByActionsFromServer(data);
              if(!scheduleTimeUpDate.isRun())
                  scheduleTimeUpDate.run();
          }
          else {
              if(scheduleTimeUpDate.isRun())
                  scheduleTimeUpDate.stop();
              Toast.makeText(context, "Error of server CODE:" + codeResponse, Toast.LENGTH_LONG).show();
          }
      }

    @Override
    public void setArrayListTextViewSensors(ArrayList<ControllerTextView> arrayListControllerTextViews) {
        this.controllerTextViews = arrayListControllerTextViews;
    }

    @Override
    public void setPreference() {
        AlertDialogSetterURL alertDialogSetterURL = new AlertDialogSetterURL(context,this);
        alertDialogSetterURL.show(ControllerUrlSharedPreference.getUrlPreference(context));
    }

    @Override
    public void setterURL(UrlPreference urlPreference)
    {
       ControllerUrlSharedPreference.putUrlPreference(context,urlPreference);
       Toast.makeText(context,"Please restart the App for apply URL",Toast.LENGTH_LONG).show();

    }

    @Override
    public void timeUpDate() {
        readingFromServer();
        Log.i("timer_schedule:::","is running...");
    }
}
