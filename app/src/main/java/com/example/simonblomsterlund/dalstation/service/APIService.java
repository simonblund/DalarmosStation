package com.example.simonblomsterlund.dalstation.service;

import com.example.simonblomsterlund.dalstation.model.Incident;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by simonblomsterlund on 16/06/2017.
 */

public interface APIService {

    @FormUrlEncoded
    @POST("url")
    Call<Incident> insertIncidentInfo(@Field("message") String message);
}
