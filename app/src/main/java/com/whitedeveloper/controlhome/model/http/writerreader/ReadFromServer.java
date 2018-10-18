package com.whitedeveloper.controlhome.model.http.writerreader;

import android.os.AsyncTask;
import com.whitedeveloper.controlhome.model.http.interfeice.IresponceFromServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ReadFromServer extends AsyncTask<Void,Void,String>
{
    private HttpURLConnection httpURLConnection;
    private IresponceFromServer iresponceFromServer;

    private int codeResponce;

    public ReadFromServer(HttpURLConnection httpURLConnection, IresponceFromServer iresponceFromServer)
    {
        this.httpURLConnection = httpURLConnection;
        this.iresponceFromServer = iresponceFromServer;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        iresponceFromServer.dataFromServer(s,codeResponce);
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        String dataFromServer = null;

        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            dataFromServer =  readFormServer(inputStream);
            inputStream.close();

            codeResponce =  httpURLConnection.getResponseCode();
        } catch (IOException e) {
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

            return stringBuilder.toString();
        }
}
