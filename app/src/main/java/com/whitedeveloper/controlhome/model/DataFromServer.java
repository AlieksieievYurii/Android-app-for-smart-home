package com.whitedeveloper.controlhome.model;

import android.util.Log;
import com.whitedeveloper.controlhome.controller.interfaces.UpDateActivity;
import com.whitedeveloper.controlhome.controller.prefaranse.UrlPreference;
import com.whitedeveloper.controlhome.model.http.ConnectorHttp;
import com.whitedeveloper.controlhome.model.http.IresponseFromServer;
import com.whitedeveloper.controlhome.model.http.writerreader.ReadFromServer;
import com.whitedeveloper.controlhome.model.http.writerreader.WriteToServer;


import java.net.HttpURLConnection;

public class DataFromServer implements IresponseFromServer {

    private UpDateActivity upDateActivity;
    private UrlPreference urlPreference;

    public DataFromServer(UrlPreference urlPreference, UpDateActivity upDateActivity)
    {
        this.urlPreference = urlPreference;
        this.upDateActivity = upDateActivity;
    }

    public void readDataFromServer(String nameServlet)
    {
        new ReadFromServer(ConnectorHttp.getConnection(urlPreference,nameServlet,"GET"),this).execute();
    }

    public void writeDataToServer(String nameServlet,String data)
    {
        new WriteToServer(ConnectorHttp.getConnection(urlPreference,nameServlet,"POST"),this).execute(data);
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
}
