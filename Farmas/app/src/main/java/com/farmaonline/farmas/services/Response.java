package com.farmaonline.farmas.services;

/**
 * Created by Romano on 20/09/2017.
 */

public class Response {

    private int httpCode;

    private String content;

    public Response(int httpCode, String content) {
        this.httpCode = httpCode;
        this.content = content;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
