package com.ryujinknee.mycars;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://bus.unifyintlco.com/TestingCar/";

    @GET("fetchCars.php")
    Call<CarsModel> getAllCars();

    @FormUrlEncoded
    @POST("fetchByName.php/{name}")
    Call<CarsModel> getCarByName(@Field("name") String name);



}
