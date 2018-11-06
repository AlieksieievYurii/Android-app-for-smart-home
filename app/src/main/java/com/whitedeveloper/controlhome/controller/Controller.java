package com.whitedeveloper.controlhome.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.whitedeveloper.controlhome.view.MainActivity;
import com.whitedeveloper.controlhome.view.activitycreator.editorviews.ActivityEditView;
import com.whitedeveloper.controlhome.controller.alertdialog.AlertDialogSetterURL;
import com.whitedeveloper.controlhome.controller.alertdialog.ISetterURL;
import com.whitedeveloper.controlhome.controller.interfaces.*;
import com.whitedeveloper.controlhome.controller.json.CreatorJsonByControllerButton;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.controlhome.controller.prefaranse.EditorViewsJson;
import com.whitedeveloper.controlhome.controller.prefaranse.UrlPreference;
import com.whitedeveloper.controlhome.model.DataFromServer;
import com.whitedeveloper.controlhome.model.cheduletimer.ItimeUpDate;
import com.whitedeveloper.controlhome.model.cheduletimer.ScheduleTimeUpDate;
import com.whitedeveloper.controlhome.view.alertdialog.IEditView;
import com.whitedeveloper.controlhome.view.alertdialog.MenuOfViewElement;
import com.whitedeveloper.custom.IonLongPressViewElement;
import com.whitedeveloper.custom.IrefreshActivity;
import com.whitedeveloper.custom.buttons.IonClickButton;
import com.whitedeveloper.custom.buttons.ControllerButton;
import com.whitedeveloper.custom.buttons.ImplementationButtons;
import com.whitedeveloper.custom.seekbar.ControllerSeekBar;
import com.whitedeveloper.custom.seekbar.ImplementationSeekBars;
import com.whitedeveloper.custom.seekbar.IonDoSeekBar;
import com.whitedeveloper.custom.textview.ControllerTextView;
import com.whitedeveloper.custom.textview.ImplementationTextView;
import org.json.JSONException;
import java.util.ArrayList;

public class Controller implements
                        UpDateActivity,
                        IonClickButton,
                        IonDoSeekBar,
                        ISetterURL,
                        ItimeUpDate,
                        IonLongPressViewElement,
                        IEditView
{
    private DataFromServer dataFromServer;
    private ScheduleTimeUpDate scheduleTimeUpDate;
    private VibrationButton vibrationButton;
    private ArrayList<ControllerButton> controllerButtons;
    private ArrayList<ControllerSeekBar> controllerSeekBars;
    private ArrayList<ControllerTextView> controllerTextViews;

    private Context context;
    private IrefreshActivity irefreshActivity;

    public  Controller(Context context, IrefreshActivity irefreshActivity)
    {
        this.context = context;
        this.irefreshActivity = irefreshActivity;
        init();
    }

    private void init()
    {
        this.dataFromServer  = new DataFromServer(
                       getUrlPreference(),
                       this);

        this.vibrationButton = new VibrationButton(context);
        this.vibrationButton.setActivated(true);

        this.scheduleTimeUpDate = new ScheduleTimeUpDate(this);
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

    public void readingFromServer()
    {
        dataFromServer.readDataFromServer();
    }

    private void sendToServer(String json)
    {
        try
        {
            Log.i("URL_ADDRESS::", getUrlPreference().getFullUrl());

        }catch (Exception e){}
        Log.i("DATA_FOR_SERVER:::",json);
        dataFromServer.writeDataToServer(json);
    }

    private UrlPreference getUrlPreference()
    {
        try {
            return ControllerSharedPreference.getUrlPreference(context);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void setArrayListButtons(ArrayList<ControllerButton> controllerButtons) {
        this.controllerButtons = controllerButtons;
        new ImplementationButtons(controllerButtons,this,this);

    }
    public void setArrayListSeekBars(ArrayList<ControllerSeekBar> controllerSeekBarArrayList) {
        this.controllerSeekBars = controllerSeekBarArrayList;
        new ImplementationSeekBars(controllerSeekBarArrayList,this,this);

    }
    public void setArrayListTextViewSensors(ArrayList<ControllerTextView> arrayListControllerTextViews) {
        this.controllerTextViews = arrayListControllerTextViews;
        new ImplementationTextView(arrayListControllerTextViews,this);
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
          if(data != null)
          {
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


    public void setPreference() {
        AlertDialogSetterURL alertDialogSetterURL = new AlertDialogSetterURL(context,this);
        alertDialogSetterURL.show(getUrlPreference());
    }

    @Override
    public void setterURL(UrlPreference urlPreference)
    {
        Log.i("TAG",urlPreference.convertToJson());
       ControllerSharedPreference.putUrlPreference(context,urlPreference);
       Toast.makeText(context,"Please restart the App for apply URL",Toast.LENGTH_LONG).show();

    }

    @Override
    public void timeUpDate() {
        readingFromServer();
        Log.i("timer_schedule:::","is running...");
    }

    @Override
    public void longPress(int id) {
        MenuOfViewElement menuOfViewElement = new MenuOfViewElement(context,id,this);
        menuOfViewElement.show();
    }

    @Override
    public void removeView(int id) {
        try {
            EditorViewsJson.removeViewWithID(id,context);
            irefreshActivity.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("TAG","Error of removing element!");
        }
    }

    @Override
    public void editView(int id)
    {
        ((AppCompatActivity)context).startActivityForResult(new Intent(context,ActivityEditView.class).putExtra("id",id), MainActivity.CODE_REQUEST_ACTIVITY_CREATE_NEW_VIEW);
    }
}
