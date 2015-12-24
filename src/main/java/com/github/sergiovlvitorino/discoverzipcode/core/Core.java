package com.github.sergiovlvitorino.discoverzipcode.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.sergiovlvitorino.discoverzipcode.Country;
import com.github.sergiovlvitorino.discoverzipcode.ResultHandler;
import com.github.sergiovlvitorino.discoverzipcode.request.URLBuilder;
import com.github.sergiovlvitorino.discoverzipcode.util.Converter;

import android.content.Context;

/**
 * Created by sergio on 09/09/15.
 */
public class Core {

    private Context context;
    private int timeout;
    private URLBuilder urlBuilder;
    private Converter converter;

    public Core(Context context, int timeout){
        this(context,timeout, new URLBuilder(), new Converter());
    }

    public Core(Context context, int timeout, URLBuilder urlBuilder, Converter converter){
        this.context = context;
        this.timeout = timeout;
        this.urlBuilder = urlBuilder;
        this.converter = converter;
    }

    public void getAddress(final Country country, final String postCode, final ResultHandler resultHandler){
        new Thread(new Runnable() {
            public void run() {
                String urlString = urlBuilder.buildURL(country, postCode);
                String resultString = request(urlString, timeout);
                resultHandler.getResult(converter.convertStringResultToAddressBean(country, resultString));
            }
        }).start();
    }

    public String request(String urlString, int timeout){
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(timeout);
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
        }finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return sb.toString();
    }
}
