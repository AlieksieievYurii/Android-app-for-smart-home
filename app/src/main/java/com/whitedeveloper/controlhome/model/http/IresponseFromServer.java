package com.whitedeveloper.controlhome.model.http;

public interface IresponseFromServer {

    void responseFromServer(int codeResponse);
    void dataFromServer(String data,int codeResponse);
}
