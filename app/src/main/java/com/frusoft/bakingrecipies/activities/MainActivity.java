package com.frusoft.bakingrecipies.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.frusoft.bakingrecipies.R;
import com.frusoft.bakingrecipies.adapters.RecipiesListAdapter;
import com.frusoft.bakingrecipies.databinding.ActivityMainBinding;
import com.frusoft.bakingrecipies.models.Recipie;
import com.frusoft.bakingrecipies.utils.RecipiesNetworkUtils;
import com.frusoft.bakingrecipies.utils.RetrofitCallUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipiesListAdapter.RecipyItemClickListener, RetrofitCallUtil.APICallbacksListener<List<Recipie>> {

    ActivityMainBinding mBinding;
    RecipiesListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //this.setContentView(R.layout.activity_main);
        mAdapter = new RecipiesListAdapter(this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBinding.recipiesRv.setLayoutManager(linearLayoutManager);


        new RecipiesNetworkUtils().getAllRecipies(this);
        mBinding.recipiesRv.setAdapter(mAdapter);

    }

    @Override
    public void onClick(Recipie recipie) {
        Toast.makeText(this, "recipie " + recipie.getName() + " tapped.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void willStartLoading() {
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<Recipie> responseBody) {
        mBinding.progressBar.setVisibility(View.INVISIBLE);
        mAdapter.setData(responseBody);
    }

    @Override
    public void onFailure() {
        mBinding.progressBar.setVisibility(View.GONE);
    }
}
