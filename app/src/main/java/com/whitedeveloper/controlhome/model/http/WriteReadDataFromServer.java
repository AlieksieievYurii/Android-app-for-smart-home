package com.whitedeveloper.controlhome.model.http;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.whitedeveloper.controlhome.model.DataFromServer;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class WriteReadDataFromServer extends AsyncTask<String, Void, String>
{
    private HttpURLConnection httpURLConnection;

    private int respoceCode;

    public WriteReadDataFromServer(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    public WriteReadDataFromServer(HttpURLConnection httpURLConnection, DataFromServer dataFromServer) {
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //ifromServer.responceFromServer(s,respoceCode);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String ... text)
    {
        String data = text[0];

        try{
            OutputStream outputStream = httpURLConnection.getOutputStream();
            writeToServer(outputStream,data);
            outputStream.close();

            this.respoceCode = httpURLConnection.getResponseCode();

            if(respoceCode == HttpURLConnection.HTTP_OK)
            {
                InputStream inputStream = httpURLConnection.getInputStream();
                String dataFromServer =  readFormServer(inputStream);
                inputStream.close();

                return dataFromServer;
            }

        } catch (Exception e) {
            e.printStackTrace(); }
        return null;
    }

    private String readFormServer(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String text;
        StringBuilder stringBuilder = new StringBuilder();

        while((text = bufferedReader.readLine()) != null)
            stringBuilder.append(text);

        return stringBuilder.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void writeToServer(OutputStream outputStream, String text) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,StandardCharsets.UTF_8));
        bufferedWriter.write(text);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
