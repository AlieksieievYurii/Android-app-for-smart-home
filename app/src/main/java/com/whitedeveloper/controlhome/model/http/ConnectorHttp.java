package com.whitedeveloper.controlhome.model.http;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectorHttp
{
    public static HttpURLConnection getConnection(String urlAdress)
    {
        try {
            URL url = new URL(urlAdress);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            return httpURLConnection;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
