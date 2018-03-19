package com.frusoft.bakingrecipies.utils;

import com.frusoft.bakingrecipies.models.Recipie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nfrugoni on 3/3/18.
 */

public interface RecipieAPIRetrofitInterface {

@GET(NetworkUtils.RECIPIES_ALL)
    Call<List<Recipie>> getAllRecipies();
}
