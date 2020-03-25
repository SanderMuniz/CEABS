package com.sandev.ceabs.ui.detalhes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sandev.ceabs.R;
import com.sandev.ceabs.dao.GeneroDAO;
import com.sandev.ceabs.model.Movie;
import com.squareup.picasso.Picasso;

import static com.sandev.ceabs.ui.lista.ListaFilmesActivity.CHAVE_INTENT;


public class DetalheActivity extends AppCompatActivity {

    private TextView titulo, descricao, lancamento, generos;
    private ImageView imagem;
    private Movie movie;
    private String URL_BASE_FOTO = "https://image.tmdb.org/t/p/original/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        iniciarViews();
        validarIntent();
        preencherCampos();
    }

    private void preencherCampos() {
        titulo.setText(movie.getTitle());
        descricao.setText(movie.getOverview());
        lancamento.setText(movie.getRelease_date());
        Picasso.get().load(URL_BASE_FOTO + movie.getPoster_path()).into(imagem);
        StringBuilder listaGeneros = defineGenero();
        generos.setText(listaGeneros);
    }

    private void validarIntent() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_INTENT))
            movie = (Movie) dados.getSerializableExtra(CHAVE_INTENT);
    }

    private void iniciarViews() {
        titulo = findViewById(R.id.label_titulo);
        descricao = findViewById(R.id.label_overview);
        lancamento = findViewById(R.id.label_lancamento);
        imagem = findViewById(R.id.label_imagem);
        generos = findViewById(R.id.label_genero);
    }

    private StringBuilder defineGenero() {
        GeneroDAO dao = new GeneroDAO();
        StringBuilder listaGeneros = new StringBuilder();
        listaGeneros.append("Generos: ");
        int[] genero = movie.getGenre_ids();
        int tamanho = genero.length;
        if (tamanho > 0) {
            for (int value : genero) {
                String retorno = dao.buscaGenero(value);
                listaGeneros.append(retorno);
            }
        } else {
            listaGeneros.append("Dado indisponível !");
        }

        return listaGeneros;
    }
}
