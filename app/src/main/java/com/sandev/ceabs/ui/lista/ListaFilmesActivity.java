package com.sandev.ceabs.ui.lista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sandev.ceabs.R;
import com.sandev.ceabs.model.Movie;
import com.sandev.ceabs.ui.lista.recyclerview.MovieAdapter;
import com.sandev.ceabs.ui.detalhes.DetalheActivity;

import java.util.List;

import static com.sandev.ceabs.ui.util.OcultarTeclado.ocultarTeclado;

public class ListaFilmesActivity extends AppCompatActivity implements ListaFilmesContract.view {

    public static String CHAVE_INTENT = "movie";
    private ListaFilmesContract.presenter mPresenter;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private EditText textoBuscar;
    private ProgressBar progressBar;
    private TextView resultados;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_filmes_activity);

        iniciarViews();
        configuraRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mPresenter == null) {
            mPresenter = new ListaFilmesPresenter();
            mPresenter.setView(this);
        }
        mPresenter.listarGeneros();
    }

    private void iniciarViews() {
        textoBuscar = findViewById(R.id.label_text_search);
        progressBar = findViewById(R.id.progress_bar);
        resultados = findViewById(R.id.label_resultado);
    }

    private void configuraRecyclerView() {
        recyclerView = findViewById(R.id.recycler_lista);
        configuraAdapter();
    }


    private void configuraAdapter() {
        adapter = new MovieAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(movie -> abreTelaDetalhe(movie));
    }

    private void abreTelaDetalhe(Movie movie) {
        Intent intent = new Intent(this, DetalheActivity.class);
        intent.putExtra(CHAVE_INTENT, movie);
        startActivity(intent);
    }

    public void buscar(View view) {
        ativarProgressBar(true);
        ocultarTeclado(this);
        pesquisarTermo();
    }

    private void pesquisarTermo() {
        mPresenter.pesquisar(textoBuscar.getText().toString());
    }

    @Override
    public void retornos(String resultado, List<Movie> lista) {
        ativarProgressBar(false);
        resultados.setText(resultado);
        adapter.atualiza(lista);
    }

    private void ativarProgressBar(boolean visivel) {
        if (visivel) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void informarUsuario(String mensagem) {
        ativarProgressBar(false);
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
