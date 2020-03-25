package com.sandev.ceabs.retrofit;

import androidx.annotation.NonNull;

import com.sandev.ceabs.retrofit.api.MovieService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class RetrofitInicializador {

    private static final String URL_BASE = "https://api.themoviedb.org/3/";
    private final Retrofit retrofit;

    public RetrofitInicializador() {
        OkHttpClient client = configuraHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    private OkHttpClient configuraHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);
        return new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
    }

    public MovieService getMovieService(){
        return retrofit.create(MovieService.class);
    }
}
