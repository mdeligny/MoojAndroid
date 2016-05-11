package com.example.martindeligny.mooj;

/**
 * Created by martindeligny on 29/04/2016.
 */

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;


public class WikeoRestClient {
    private static final String BASE_URL = "http://ns3265740.ip-37-59-53.eu:3000";
    private static AsyncHttpClient client = new AsyncHttpClient();

    private static ArrayList<Deal> deals = new ArrayList<>();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        //client.setBasicAuth("wikeo", "oekiw");
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static ArrayList<Deal> getPublicTimeline() throws JSONException {

        WikeoRestClient.get("/deals", new RequestParams(), new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

                Date date = new Date();

                try {
                    JSONArray contents = response.optJSONArray("content");
                    deals.clear();

                    for (int i = 0; i < contents.length(); i++) {

                        JSONObject content = contents.getJSONObject(i);

                        String name = content.optString("name");
                        String description = content.optString("description");

                        deals.add(new Deal(name, description, date));

                        Log.i("fetch deal", deals.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray contents) {
                // Pull out the first event on the public timeline

                Log.i("Reponse", contents.toString());

                Date date = new Date();

                try {
                    deals.clear();

                    for (int i = 0; i < contents.length(); i++) {

                        JSONObject content = contents.getJSONObject(i);

                        String name = content.optString("name");
                        String description = content.optString("description");

                        deals.add(new Deal(name, description, date));

                        Log.i("fetch deal", deals.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.e("error", responseString);
            }
        });

        Log.i("deals wk rest client", deals.toString());

        return deals;
    }
}
