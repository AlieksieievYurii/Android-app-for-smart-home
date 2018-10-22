package com.whitedeveloper.controlhome.model.http.writerreader;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import com.whitedeveloper.controlhome.model.http.interfeice.IresponceFromServer;

import java.io.*;
import java.net.HttpURLConnection;

public class WriteToServer extends AsyncTask<String,Void,Integer>
{
    private IresponceFromServer iresponceFromServer;
    private HttpURLConnection httpURLConnection;
    private int responceCode;

    public WriteToServer(HttpURLConnection httpURLConnection, IresponceFromServer iresponceFromServer) {
        this.iresponceFromServer = iresponceFromServer;
        this.httpURLConnection = httpURLConnection;
    }

    protected Integer doInBackground(String ... text)
    {
        String data = text[0];

        try{
            OutputStream outputStream = httpURLConnection.getOutputStream();
            writeToServer(outputStream,String.format("data=%s",data));
            outputStream.close();

            this.responceCode = httpURLConnection.getResponseCode();


           } catch (Exception e) {
            e.printStackTrace(); }

            return responceCode;
       }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        iresponceFromServer.responceFromServer(integer);
    }

    private void writeToServer(OutputStream outputStream, String text) throws IOException {
           BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
           bufferedWriter.write(text);
           bufferedWriter.flush();
           bufferedWriter.close();
       }
}
