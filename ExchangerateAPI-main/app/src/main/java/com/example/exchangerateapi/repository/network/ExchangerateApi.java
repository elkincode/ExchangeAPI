package com.example.exchangerateapi.repository.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ExchangerateApi {
    @GET("{token}/pair/{firstValue}/{lastValue}")
    Call<Values> getMultiplier(@Path("firstValue") String value,
                               @Path("lastValue") String lastValue,
                               @Path("token") String token);
}
