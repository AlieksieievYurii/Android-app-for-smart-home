package com.whitedeveloper.controlhome.model;

import android.util.Log;
import com.whitedeveloper.controlhome.controller.interfaces.UpDateActiviti;
import com.whitedeveloper.controlhome.model.http.ConnectorHttp;
import com.whitedeveloper.controlhome.model.http.interfeice.IresponceFromServer;
import com.whitedeveloper.controlhome.model.http.writerreader.ReadFromServer;
import com.whitedeveloper.controlhome.model.http.writerreader.WriteToServer;


import java.net.HttpURLConnection;

public class DataFromServer implements IresponceFromServer {

    private UpDateActiviti upDateActiviti;
    private String url;
    private String key;

    public DataFromServer(String url,String key, UpDateActiviti upDateActiviti)
    {
        this.url = url;
        this.key = key;
        this.upDateActiviti = upDateActiviti;
    }

    public void readDataFromServer(String nameServlet)
    {
        new ReadFromServer(ConnectorHttp.getConnection(url.concat(nameServlet),key,"GET"),this).execute();
    }

    public void writeDataToServer(String nameServlet,String data)
    {
        new WriteToServer(ConnectorHttp.getConnection(url.concat(nameServlet),key,"POST"),this).execute(data);
    }


    @Override
    public void responceFromServer(int codeResponce) {
        Log.i("RESPONCE_FOR_RADING",String.valueOf(codeResponce));
    }

    @Override
    public void dataFromServer(String data, int codeResponce)
    {
        Log.i("RESPONCE", String.valueOf(codeResponce));
        if(codeResponce == HttpURLConnection.HTTP_OK)
            upDateActiviti.updateActiviti(data);
    }
}
