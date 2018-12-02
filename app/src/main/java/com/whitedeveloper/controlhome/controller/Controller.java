package com.whitedeveloper.controlhome.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.whitedeveloper.controlhome.R;
import com.whitedeveloper.controlhome.view.MainActivity;
import com.whitedeveloper.controlhome.view.activitycreator.editorviews.ActivityEditView;
import com.whitedeveloper.controlhome.view.alertdialog.AlertDialogSetterURL;
import com.whitedeveloper.controlhome.view.alertdialog.ISetterURL;
import com.whitedeveloper.controlhome.controller.interfaces.*;
import com.whitedeveloper.controlhome.controller.json.CreatorJsonForTCOD;
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

import static com.whitedeveloper.TagKeys.CODE_REQUEST_ACTIVITY_CREATE_NEW_VIEW;

public class Controller implements
        UpDateActivity,
        IonClickButton,
        IonDoSeekBar,
        ISetterURL,
        ItimeUpDate,
        IonLongPressViewElement,
        IEditView {
    private DataFromServer dataFromServer;
    private ScheduleTimeUpDate scheduleTimeUpDate;
    private VibrationButton vibrationButton;
    private ArrayList<ControllerButton> controllerButtons;
    private ArrayList<ControllerSeekBar> controllerSeekBars;
    private ArrayList<ControllerTextView> controllerTextViews;

    private final Context context;
    private final IrefreshActivity irefreshActivity;

    public Controller(Context context, IrefreshActivity irefreshActivity) {
        this.context = context;
        this.irefreshActivity = irefreshActivity;
        init();
    }

    private void init() {
        this.dataFromServer = new DataFromServer(
                context,
                getUrlPreference(),
                this);

        this.vibrationButton = new VibrationButton(context);
        this.vibrationButton.setActivated(true);

        this.scheduleTimeUpDate = new ScheduleTimeUpDate(this);
    }


    private void runWithServer(int id) {
        CreatorJsonForTCOD creatorJsonForTCOD = new CreatorJsonForTCOD(
                controllerButtons,
                controllerSeekBars);

        try {
            String object = creatorJsonForTCOD.getJsonOfElement(id);
            sendToServer(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkTheChanges();
    }

    private void setViewsByActionsFromServer(String data) {
        SetterStatusViewFRomServer.setPropertyHashCode(context, data);

        try {
            SetterStatusViewFRomServer.setButtonsStatus(this.controllerButtons, data);
            SetterStatusViewFRomServer.setSeekBarsStatus(this.controllerSeekBars, data);
            SetterStatusViewFRomServer.setTextViewSensors(this.controllerTextViews, data);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void checkTheChanges() {
        dataFromServer.readDataFromServerByHashCode();
    }

    public void readOnceFromServer() {
        dataFromServer.readDataFromServer();
    }

    private void sendToServer(String json) {
        dataFromServer.writeDataToServer(json);
    }

    private UrlPreference getUrlPreference() {
        try {
            return ControllerSharedPreference.getUrlPreference(context);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void setArrayListButtons(ArrayList<ControllerButton> controllerButtons) {
        this.controllerButtons = controllerButtons;
        new ImplementationButtons(controllerButtons, this, this);

    }

    public void setArrayListSeekBars(ArrayList<ControllerSeekBar> controllerSeekBarArrayList) {
        this.controllerSeekBars = controllerSeekBarArrayList;
        new ImplementationSeekBars(controllerSeekBarArrayList, this, this);

    }

    public void setArrayListTextViewSensors(ArrayList<ControllerTextView> arrayListControllerTextViews) {
        this.controllerTextViews = arrayListControllerTextViews;
        new ImplementationTextView(arrayListControllerTextViews, this);
    }


    @Override
    public void onClickButton(int id) {
        runWithServer(id);
        vibrationButton.pressEffect();
    }


    @Override
    public void onDoSeekBar(int id) {

        runWithServer(id);
        vibrationButton.pressEffect();
    }

    @Override
    public void updateActivity(String data, int codeResponse) {
        if (data != null) {
            setViewsByActionsFromServer(data);

            if (!scheduleTimeUpDate.isRun())
                scheduleTimeUpDate.run();
        } else {
            stopProcess();
            Toast.makeText(context, context.getString(R.string.error_of_server) + codeResponse, Toast.LENGTH_LONG).show();
        }
    }


    public void setPreference() {
        AlertDialogSetterURL alertDialogSetterURL = new AlertDialogSetterURL(context, this);
        alertDialogSetterURL.show(getUrlPreference());
    }

    @Override
    public void setterURL(UrlPreference urlPreference) {
        ControllerSharedPreference.putUrlPreference(context, urlPreference);
        Toast.makeText(context, R.string.restart_app, Toast.LENGTH_LONG).show();
    }

    @Override
    public void timeUpDate() {
        checkTheChanges();
    }

    @Override
    public void longPress(int id) {
        MenuOfViewElement menuOfViewElement = new MenuOfViewElement(context, id, this);
        menuOfViewElement.show();
    }

    @Override
    public void removeView(int id) {
        try {
            EditorViewsJson.removeViewWithID(id, context);
            irefreshActivity.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editView(int id) {
        ((AppCompatActivity) context).startActivityForResult(new Intent(context, ActivityEditView.class).putExtra("id", id), CODE_REQUEST_ACTIVITY_CREATE_NEW_VIEW);
    }

    public void stopProcess() {
        if (scheduleTimeUpDate.isRun())
            scheduleTimeUpDate.stop();
    }

}
