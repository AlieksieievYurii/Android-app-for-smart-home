package com.whitedeveloper.controlhome.model.http;


import com.whitedeveloper.controlhome.controller.prefaranse.UrlPreference;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectorHttp
{
    public static HttpURLConnection getConnection(UrlPreference urlPreference, Request request)
    {
        try {
            return getConnection(urlPreference.getFullUrl(),request);
        }catch (Exception error)
        {
            error.printStackTrace();
            return null;
        }
    }

    public static HttpURLConnection getConnection(String urlString, Request request)
        {
            try {
                final URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod(request.toString());
                httpURLConnection.setConnectTimeout(20000);
                httpURLConnection.setReadTimeout(20000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(request.equals(Request.POST));
                return httpURLConnection;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
}
