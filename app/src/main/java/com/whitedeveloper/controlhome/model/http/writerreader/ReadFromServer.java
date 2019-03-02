package com.whitedeveloper.controlhome.model.http.writerreader;

import android.os.AsyncTask;
import com.whitedeveloper.controlhome.model.http.IresponseFromServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ReadFromServer extends AsyncTask<Void,Void,String>
{
    private final HttpURLConnection httpURLConnection;
    private final IresponseFromServer iresponseFromServer;

    private int codeResponse;

    public ReadFromServer(HttpURLConnection httpURLConnection, IresponseFromServer iresponseFromServer)
    {
        this.httpURLConnection = httpURLConnection;
        this.iresponseFromServer = iresponseFromServer;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iresponseFromServer.dataFromServer(s, codeResponse);
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        String dataFromServer = null;

        try {
           final InputStream inputStream = httpURLConnection.getInputStream();
            dataFromServer =  readFormServer(inputStream);
            inputStream.close();

            codeResponse =  httpURLConnection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataFromServer;
    }
    private String readFormServer(InputStream inputStream) throws IOException {

            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String text;
            final StringBuilder stringBuilder = new StringBuilder();

            while((text = bufferedReader.readLine()) != null)
                stringBuilder.append(text);

            bufferedReader.close();

            return stringBuilder.toString();
        }
}
