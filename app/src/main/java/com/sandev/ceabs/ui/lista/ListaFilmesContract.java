package com.sandev.ceabs.ui.lista;

import android.content.Context;

import com.sandev.ceabs.model.Movie;
import com.sandev.ceabs.model.Resposta;
import com.sandev.ceabs.model.RespostaGenero;

import java.util.List;

import retrofit2.Response;

public interface ListaFilmesContract {

    interface view {
        void retornos(String resultado, List<Movie> listaFilmes);

        void informarUsuario(String mensagem);
    }

    interface presenter {
        void setView(view view);

        Context getContext();

        void listarGeneros();

        void generos(Response<RespostaGenero> response);

        void pesquisar(String buscar);

        void retorno(Response<Resposta> response);

        void erroAoBuscar();
    }


    interface call {
        void buscarFilme(String buscar);

        void listarGeneros();
    }
}
