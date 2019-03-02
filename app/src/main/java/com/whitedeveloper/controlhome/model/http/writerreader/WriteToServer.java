package com.whitedeveloper.controlhome.model.http.writerreader;

import android.os.AsyncTask;
import com.whitedeveloper.controlhome.model.http.IresponseFromServer;

import java.io.*;
import java.net.HttpURLConnection;

public class WriteToServer extends AsyncTask<String,Void,Integer>
{
    private final IresponseFromServer iresponseFromServer;
    private final HttpURLConnection httpURLConnection;
    private int responseCode;

    public WriteToServer(HttpURLConnection httpURLConnection, IresponseFromServer iresponseFromServer) {
        this.iresponseFromServer = iresponseFromServer;
        this.httpURLConnection = httpURLConnection;
    }

    protected Integer doInBackground(String ... text)
    {
        String data = text[0];

        try{
            OutputStream outputStream = httpURLConnection.getOutputStream();
            writeToServer(outputStream,String.format("data=%s",data));
            outputStream.close();

            this.responseCode = httpURLConnection.getResponseCode();


           } catch (Exception e) {
            e.printStackTrace(); }

            return responseCode;
       }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        iresponseFromServer.responseFromServer(integer);
    }

    private void writeToServer(OutputStream outputStream, String text) throws IOException {
           BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
           bufferedWriter.write(text);
           bufferedWriter.flush();
           bufferedWriter.close();
       }
}
