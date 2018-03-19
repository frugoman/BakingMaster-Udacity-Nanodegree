package com.frusoft.bakingrecipies.utils;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nfrugoni on 3/3/18.
 */

public class RetrofitCallUtil<T> {

    public interface APICallbacksListener<T> {
        void willStartLoading();

        void onSuccess(T responseBody);

        void onFailure();
    }

    public void GET(Call call, final APICallbacksListener<T> listener) {
        listener.willStartLoading();
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body());
                        break;
                    default:
                        listener.onFailure();
                        break;
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
