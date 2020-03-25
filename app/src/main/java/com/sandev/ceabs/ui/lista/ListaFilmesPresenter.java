package com.sandev.ceabs.ui.lista;

import android.content.Context;
import android.text.TextUtils;

import com.sandev.ceabs.dao.GeneroDAO;
import com.sandev.ceabs.model.Resposta;
import com.sandev.ceabs.model.RespostaGenero;

import retrofit2.Response;

public class ListaFilmesPresenter implements ListaFilmesContract.presenter {

    private final ListaFilmesCall mCall;
    private final GeneroDAO dao = new GeneroDAO();
    private ListaFilmesContract.view mView;

    public ListaFilmesPresenter() {
        mCall = new ListaFilmesCall(this);
    }

    @Override
    public void setView(ListaFilmesContract.view view) {
        this.mView = view;
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void pesquisar(String buscar) {
        if (!TextUtils.isEmpty(buscar)) {
            mCall.buscarFilme(buscar);
        } else {
            mView.informarUsuario("Necessário informa um título !");
        }

    }

    @Override
    public void retorno(Response<Resposta> response) {
        if (response.isSuccessful()) {
            Resposta resposta = response.body();
            if (resposta != null) {
                mView.retornos(resposta.itensRetornados(), resposta.getResults());
            } else {
                mView.informarUsuario("Nenhum dado foi encontrado !");
            }
        } else {
            mView.informarUsuario("Erro ao recuperar dados, realize a busca novamente !");
        }
    }

    @Override
    public void erroAoBuscar() {
        mView.informarUsuario("Erro ao buscar dados, realize a busca novamente !");
    }

    @Override
    public void listarGeneros() {
        mCall.listarGeneros();
    }

    @Override
    public void generos(Response<RespostaGenero> response) {
        if (response.isSuccessful()) {
            RespostaGenero body = response.body();
            if (body != null) {
                dao.setarGeneros(body.getGeneros());
            }
        }
    }
}
