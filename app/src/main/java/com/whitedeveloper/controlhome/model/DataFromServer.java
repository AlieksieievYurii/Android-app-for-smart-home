package com.whitedeveloper.controlhome.model;

import android.content.Context;
import android.util.Log;
import com.whitedeveloper.controlhome.controller.interfaces.UpDateActivity;
import com.whitedeveloper.controlhome.controller.prefaranse.ControllerSharedPreference;
import com.whitedeveloper.controlhome.controller.prefaranse.UrlPreference;
import com.whitedeveloper.controlhome.model.http.ConnectorHttp;
import com.whitedeveloper.controlhome.model.http.HashFromServer;
import com.whitedeveloper.controlhome.model.http.IresponseFromServer;
import com.whitedeveloper.controlhome.model.http.writerreader.ReadFromServer;
import com.whitedeveloper.controlhome.model.http.writerreader.ReaderServerHashCode;
import com.whitedeveloper.controlhome.model.http.writerreader.WriteToServer;


import java.net.HttpURLConnection;

public class DataFromServer implements IresponseFromServer, HashFromServer {

    private final UpDateActivity upDateActivity;
    private final UrlPreference urlPreference;
    private final Context context;

    public DataFromServer(Context context, UrlPreference urlPreference, UpDateActivity upDateActivity)
    {
        this.urlPreference = urlPreference;
        this.upDateActivity = upDateActivity;
        this.context = context;
    }

    public void readDataFromServerByHashCode()
    {
        new ReaderServerHashCode(ConnectorHttp.getConnection(urlPreference.getHushUrl(),"GET"),this).execute();
    }

    public void readDataFromServer()
    {
        Log.i("TEST_READ","LOL");
        new ReadFromServer(ConnectorHttp.getConnection(urlPreference, "GET"), this).execute();
    }

    public void writeDataToServer(String data)
    {
        new WriteToServer(ConnectorHttp.getConnection(urlPreference,"POST"),this).execute(data);
    }

    private boolean checkHashCode(String hashCodeFromServer)
    {
        return ControllerSharedPreference.getHashCode(context).equals(hashCodeFromServer);
    }

    @Override
    public void responseFromServer(int codeResponse) {
        Log.i("RESPONSE_FOR_WRITING::",String.valueOf(codeResponse));
    }

    @Override
    public void dataFromServer(String data, int codeResponse)
    {
        Log.i("RESPONSE_FOR_READING::", String.valueOf(codeResponse));
        if(codeResponse == HttpURLConnection.HTTP_OK)
            upDateActivity.updateActivity(data,codeResponse);
        else
            upDateActivity.updateActivity(null,codeResponse);
    }

    @Override
    public void hashCodeFromServer(String hashCode, int codeResponse) {

        if (codeResponse == HttpURLConnection.HTTP_OK) {
            Log.i("HASH",hashCode);
            if (!checkHashCode(hashCode))
            {
                Log.i("HASH","HASH CODES ARE DIFFERENCE");
                readDataFromServer();
            }
        } else
            upDateActivity.updateActivity(null, codeResponse);
    }
}
