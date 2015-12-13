package com.angarron.sfvframedata.network;

import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.http.GET;

public interface VFramesRESTApi {
    @GET("/res/vframes_data.json")
    Call<JsonObject> getData();
}