package com.westside.restful;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brian on 2016-07-03.
 */
public class HttpPutAsyncTask extends AsyncTask<Void, Void, String> {
    private AsyncEventHandler eventHandler;
    private String http;
    private int message;
    private Map<String, Object> headerParams;
    private String payload;
    private static final String TAG = "RESTFUL";

    public HttpPutAsyncTask(String http, AsyncEventHandler eventHandler, int message, Map<String, Object> headerParams, String payload) {
        this.http = http;
        this.eventHandler = eventHandler;
        this.message = message;
        this.headerParams = headerParams;
        this.payload = payload;
    }

    @Override
    protected  void onPreExecute() {
        eventHandler.onAsyncTaskStarted(message);
    }


    @Override
    protected String doInBackground(Void... params) {

        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL(http);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("PUT");


            if (headerParams != null) {
                for (Map.Entry<String, Object> entry : headerParams.entrySet()) {
                    con.setRequestProperty(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }


            if (payload != null) {
                OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                out.write(payload);
                out.flush();
                out.close();
            }


            //display what returns the POST request


            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                Log.d(TAG, "" + sb.toString());
            } else {
                Log.d(TAG, con.getResponseMessage());
            }

        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }

        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        eventHandler.onAsyncTaskFinished(message, result);
    }
}
