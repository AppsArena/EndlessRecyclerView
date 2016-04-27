package com.pratap.endlessrecyclerview;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import Libraries.Connectivity;
import Libraries.GsonRequest;
import Libraries.NetworkRetryPolicy;
import Libraries.RequestCallback;
import Libraries.Volley;

/**
 * Created by has9 on 3/28/16.
 */
public class CrazyDealRequest {

    private static final String TAG = "DealCrazyDealsRequest";

    private String body = "{\n" +
            " \"LowerLimit\" : \"0\",\n" +
            " \"UpperLimit\" : \"50\",\n" +
            "}\n";
    public void getDealDetailsCrazyDealsRequest(final Context context,
                                                final RequestCallback<CrazyData[]> requestCallback) {
        String url = "http://api.ajkerdeal.com/CrazyDeals/GetCrazyDeals";
        if(!Connectivity.isInternetConnected(context)) {
            Log.e(TAG, "getDealDetails: No Internet");
            return;
        }
        GsonRequest<CrazyData[]> serviceCall = new GsonRequest<>(Request.Method.POST, url,
                CrazyData[].class, null, body, new Response.Listener<CrazyData[]>() {
            @Override
            public void onResponse(CrazyData[] response) {
                requestCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ", error);
            }
        });
        serviceCall.setRetryPolicy(NetworkRetryPolicy.getRetryPolicyForFifteenSecond());
        Volley.getInstance(context).addToRequestQueue(serviceCall);
    }


    public void getCrazyDeals(final Context context,
                              final RequestCallback<CrazyData[]> requestCallback,
                              final LimitModel limit) {
        String url = "http://192.168.0.139/CrazyDeals/GetCrazyDeals";
        Gson gson = new Gson();
        String requestBody = gson.toJson(limit);
        if(!Connectivity.isInternetConnected(context)) {
            Log.e(TAG, "getCrazyDeals: No Internet");
            return;
        }

        GsonRequest<CrazyData[]> serviceCall = new GsonRequest<>(Request.Method.POST, url,
                CrazyData[].class, null, requestBody, new Response.Listener<CrazyData[]>() {
            @Override
            public void onResponse(CrazyData[] response) {
                requestCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ", error);
            }
        });
        serviceCall.setRetryPolicy(NetworkRetryPolicy.getRetryPolicyForFifteenSecond());
        Volley.getInstance(context).addToRequestQueue(serviceCall);
    }

    public void getCrazyDealsCount(final Context context,
                                   final RequestCallback<CountModel> requestCallback) {
        String url = "http://192.168.0.139/CrazyDeals/GetCrazyDealsCount";
        if(!Connectivity.isInternetConnected(context)) {
            Log.e(TAG, "getCrazyDeals: No Internet");
            return;
        }

        GsonRequest<CountModel> serviceCall = new GsonRequest<>(Request.Method.GET, url,
                CountModel.class, null, null,
                new Response.Listener<CountModel>() {
                    @Override
                    public void onResponse(CountModel response) {
                        requestCallback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: ", error);
                    }
                }
        );
    }
}
