package com.sandev.ceabs.ui.lista;

import android.util.Log;

import com.sandev.ceabs.model.Resposta;
import com.sandev.ceabs.model.RespostaGenero;
import com.sandev.ceabs.retrofit.RetrofitInicializador;
import com.sandev.ceabs.retrofit.api.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaFilmesCall implements ListaFilmesContract.call {

    private final String token = "53133282dc48b97f36b50a6a54ac8d48";
    private final String language = "pt-BR";
    private final ListaFilmesContract.presenter mPresenter;
    private MovieService service;

    public ListaFilmesCall(ListaFilmesContract.presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void buscarFilme(String buscar) {
        service = new RetrofitInicializador().getMovieService();
        Call<Resposta> listCall = service.searchMovie(token, language, buscar);
        listCall.enqueue(new Callback<Resposta>() {
            @Override
            public void onResponse(Call<Resposta> call, Response<Resposta> response) {
                mPresenter.retorno(response);
            }

            @Override
            public void onFailure(Call<Resposta> call, Throwable t) {
                mPresenter.erroAoBuscar();
            }
        });
    }

    @Override
    public void listarGeneros() {
        service = new RetrofitInicializador().getMovieService();
        Call<RespostaGenero> generos = service.getGeneros(token, language);
        generos.enqueue(new Callback<RespostaGenero>() {
            @Override
            public void onResponse(Call<RespostaGenero> call, Response<RespostaGenero> response) {
                mPresenter.generos(response);
            }

            @Override
            public void onFailure(Call<RespostaGenero> call, Throwable t) {

            }
        });
    }
}