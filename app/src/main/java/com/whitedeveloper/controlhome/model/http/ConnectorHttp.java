package com.whitedeveloper.controlhome.model.http;


import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectorHttp
{
    public static HttpURLConnection getConnection(String urlAdress,String key,String requestMethod)
    {
        try {
            URL url = new URL(urlAdress.concat("?key=").concat(key));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod(requestMethod);
           httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(requestMethod.equals("POST"));
            return httpURLConnection;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
