package com.farmaonline.farmas.services;

import org.json.JSONArray;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.util.EntityUtils;
import util.Constants;
import util.Util;

/**
 * Created by Romano on 18/09/2017.
 */

public class Request {

    public static Response post(String url, String contentType, String args) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(url);

        post.addHeader("Content-Type", contentType);
        post.addHeader("Accept", contentType);

        StringEntity strEntity = new StringEntity(args);

        post.setEntity(strEntity);

        HttpResponse response = httpClient.execute(post);

        Response res = new Response(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity()));

        return res;
    }

    public static Response get(String url, String contentType, String params) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        if (!Util.isNullOrEmpty(params)) {
            url =  url + "?" + params;
        }

        HttpGet clientGet = new HttpGet(url);

        clientGet.addHeader("Content-Type", contentType);

        HttpResponse response = httpClient.execute(clientGet);

        String json = null;
        try {
            json = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Response(response.getStatusLine().getStatusCode(), json);
    }
}
