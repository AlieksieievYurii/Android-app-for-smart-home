package com.whitedeveloper.controlhome.model;

import android.util.Log;
import com.whitedeveloper.controlhome.controller.interfaces.UpDateActiviti;
import com.whitedeveloper.controlhome.model.http.ConnectorHttp;
import com.whitedeveloper.controlhome.model.http.WriteReadDataFromServer;
import com.whitedeveloper.controlhome.model.http.interfeice.IresponceFromServer;
import com.whitedeveloper.controlhome.model.http.writerreader.ReadFromServer;
import com.whitedeveloper.controlhome.model.http.writerreader.WriteToServer;


import java.net.HttpURLConnection;

public class DataFromServer implements IresponceFromServer {

    private WriteReadDataFromServer writeReadDataFromServer;
    private UpDateActiviti upDateActiviti;
    private String url;
    private String key;

    public DataFromServer(String url,String key, UpDateActiviti upDateActiviti)
    {
        this.url = url;
        this.key = key;
        this.upDateActiviti = upDateActiviti;
    }

    public void readDataFromServer()
    {
        new ReadFromServer(ConnectorHttp.getConnection(url),this).execute();
    }

    public void writeDataToServer(String data)
    {
        new WriteToServer(ConnectorHttp.getConnection(url),this).execute(key,data);
    }


    @Override
    public void responceFromServer(int codeResponce) {
        Log.i("RESPONCE_FROM_SERVER",String.valueOf(codeResponce));
    }

    @Override
    public void dataFromServer(String data, int codeResponce)
    {
        Log.i("RESPONCE_DATA_SERVER",codeResponce+"");
        if(codeResponce == HttpURLConnection.HTTP_OK)
            upDateActiviti.updateActiviti(data);
    }
}
