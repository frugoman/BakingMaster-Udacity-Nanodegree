package com.frusoft.bakingrecipies.utils;

import android.util.Log;

import com.frusoft.bakingrecipies.models.Recipie;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nfrugoni on 3/3/18.
 */

public class RecipiesNetworkUtils {

    static RetrofitCallUtil retrofitCallUtil = new RetrofitCallUtil();

    public void getAllRecipies(RetrofitCallUtil.APICallbacksListener<List<Recipie>> apiCallbacksListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipieAPIRetrofitInterface apiService = retrofit.create(RecipieAPIRetrofitInterface.class);
        Call<List<Recipie>> allRecipies = apiService.getAllRecipies();
        retrofitCallUtil.GET(allRecipies, apiCallbacksListener);
    }
}
