package com.whitedeveloper.controlhome.model.http;


import com.whitedeveloper.controlhome.controller.prefaranse.UrlPreference;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectorHttp
{
    public static HttpURLConnection getConnection(UrlPreference urlPreference, String requestMethod)
    {
        try {
            return getConnection(urlPreference.getFullUrl(),requestMethod);
        }catch (Exception error)
        {
            error.printStackTrace();
            return null;
        }
    }

    public static HttpURLConnection getConnection(String urlString, String requestMethod)
        {
            try {
                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod(requestMethod);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(requestMethod.equals("POST"));
                return httpURLConnection;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
}
