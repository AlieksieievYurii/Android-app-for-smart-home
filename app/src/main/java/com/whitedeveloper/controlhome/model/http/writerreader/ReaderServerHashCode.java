package com.whitedeveloper.controlhome.model.http.writerreader;

import android.os.AsyncTask;
import com.whitedeveloper.controlhome.model.http.HashFromServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ReaderServerHashCode extends AsyncTask<Void,Void,String> {

    private HttpURLConnection httpURLConnection;
    private HashFromServer hashFromServer;
    private int codeResponse = 0;

    public ReaderServerHashCode(HttpURLConnection httpURLConnection, HashFromServer hashFromServer) {
        this.httpURLConnection = httpURLConnection;
        this.hashFromServer = hashFromServer;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        hashFromServer.hashCodeFromServer(s,codeResponse);
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        String dataFromServer = null;

        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            dataFromServer =  readFormServer(inputStream);
            inputStream.close();

            codeResponse =  httpURLConnection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataFromServer;
    }

    private String readFormServer(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String text;
        StringBuilder stringBuilder = new StringBuilder();

        while((text = bufferedReader.readLine()) != null)
            stringBuilder.append(text);

        bufferedReader.close();

        return stringBuilder.toString();
    }
}
