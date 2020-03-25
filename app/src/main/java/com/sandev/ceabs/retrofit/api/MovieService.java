package com.sandev.ceabs.retrofit.api;

import com.sandev.ceabs.model.Resposta;
import com.sandev.ceabs.model.RespostaGenero;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("genre/movie/list")
    Call<RespostaGenero> getGeneros(
            @Query("api_key") String api_key,
            @Query("language") String language);

    @GET("search/movie")
    Call<Resposta> searchMovie(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query
    );

}
