package com.whitedeveloper.controlhome.model.http;

public enum Request
{
    GET("GET"),POST("POST");

    private String request;

    Request(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return request;
    }

    public boolean equals(Request request)
    {
        return request.toString().equals(this.toString());
    }

}
