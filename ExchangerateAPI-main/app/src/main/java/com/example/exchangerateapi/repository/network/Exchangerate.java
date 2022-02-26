package com.example.exchangerateapi.repository.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Exchangerate {
    private ExchangerateApi api;

    public Exchangerate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v6.exchangerate-api.com/v6/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ExchangerateApi.class);
    }

    public LiveData<Double> getValue(String convertFrom, String convertTo) {
        MutableLiveData<Double> convertRate = new MutableLiveData<>();
        Call<Values> call = api.getMultiplier(convertFrom, convertTo, "cb119f3fe95b0ed04cb615f9");
        call.enqueue(new Callback<Values>() {

            @Override
            public void onResponse(@NonNull Call<Values> call, @NonNull Response<Values> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Values values = response.body();
                    convertRate.setValue((double) values.getConversion_rate());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Values> call, Throwable t) {
                return;
            }
        });
        return convertRate;
    }
}

