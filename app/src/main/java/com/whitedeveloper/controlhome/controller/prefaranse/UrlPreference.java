package com.whitedeveloper.controlhome.controller.prefaranse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class UrlPreference implements Serializable
{
    private static final String KEY_URL = "url";
    private static final String KEY_NAME_ADDITION_PATH = "name_key_addition_path";
    private static final String KEY_PATH_TO_HASH= "path_to_hush_sum";
    private static final String KEY_NAME_KEY_PARAM = "name_key_param";
    private static final String KEY_SEVER_KEY = "key";

    private String url;
    private String additionPath;
    private String pathToHashSum;
    private String nameKeyParameter;
    private String key;

    public String getUrl() {
        return url;
    }

    public String getAdditionPath() {
          return additionPath;
      }

    public String getNameKeyParameter() {
        return nameKeyParameter;
    }

    public String getKey() {
        return key;
    }

    public String getPathToHashSum() {
        return pathToHashSum;
    }

    public UrlPreference(String url, String additionPath, String pathToHashSum, String nameKeyParameter, String key) {
        this.url = url;
        this.additionPath = additionPath;
        this.pathToHashSum = pathToHashSum;
        this.nameKeyParameter = nameKeyParameter;
        this.key = key;
    }
    UrlPreference(String json) throws Exception {
        convertFromJSON(json);
    }

    private void convertFromJSON(String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json);

        url = jsonObject.getString(KEY_URL);
        additionPath = jsonObject.getString(KEY_NAME_ADDITION_PATH);
        pathToHashSum = jsonObject.getString(KEY_PATH_TO_HASH);
        nameKeyParameter = jsonObject.getString(KEY_NAME_KEY_PARAM);
        key = jsonObject.getString(KEY_SEVER_KEY);
    }

    String convertToJson()
    {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put(KEY_URL,url);
            jsonObject.put(KEY_NAME_ADDITION_PATH,additionPath);
            jsonObject.put(KEY_PATH_TO_HASH,pathToHashSum);
            jsonObject.put(KEY_NAME_KEY_PARAM,nameKeyParameter);
            jsonObject.put(KEY_SEVER_KEY,key);

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFullUrl()// For example: http://google.com/...additionPath ..?key=12123
    {
        if(url.equals(""))
            return "";

        final StringBuilder stringBuilder = new StringBuilder(url);

        if(!url.substring(url.length() - 1).equals("/"))
            stringBuilder.append("/");
        stringBuilder.append(additionPath);

        if(nameKeyParameter != null) {
            stringBuilder.append("?").append(nameKeyParameter).append("=");
            stringBuilder.append(key);
        }

        return stringBuilder.toString();
    }

    public String getHushUrl()
    {
        if(url.equals(""))
            return "";

        final StringBuilder stringBuilder = new StringBuilder(url);
        if(!url.substring(url.length() - 1).equals("/"))
            stringBuilder.append("/");
        stringBuilder.append(pathToHashSum);

        return stringBuilder.toString();
    }
}
