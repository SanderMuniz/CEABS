package com.sandev.ceabs.ui.lista.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandev.ceabs.R;
import com.sandev.ceabs.dao.GeneroDAO;
import com.sandev.ceabs.model.Movie;
import com.sandev.ceabs.ui.lista.recyclerview.adapter.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Context context;
    private List<Movie> lista;
    private OnItemClickListener onItemClickListener;

    public MovieAdapter(Context context) {
        this.context = context;
        this.lista = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.card_item_list, parent, false);

        return new MovieViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie movie = lista.get(position);

        holder.vincula(movie);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void atualiza(List<Movie> lista) {
        this.lista.clear();
        this.lista.addAll(lista);
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private Movie movie;
        private TextView titulo, lancamento, generos;
        private ImageView imagem;
        private String URL_BASE_FOTO = "https://image.tmdb.org/t/p/original/";

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_titulo);
            generos = itemView.findViewById(R.id.item_genero);
            lancamento = itemView.findViewById(R.id.item_lancamento);
            imagem = itemView.findViewById(R.id.label_imagem);
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(movie));
        }

        public void vincula(Movie movie) {
            this.movie = movie;
            preencheCampos();
        }

        private void preencheCampos() {
            titulo.setText(movie.getTitle());
            lancamento.setText(movie.getRelease_date());
            if (movie.getPoster_path() != null){
                Picasso.get().load(URL_BASE_FOTO + movie.getPoster_path()).into(imagem);
            }else {
                imagem.setVisibility(View.GONE);
            }

            StringBuilder listaGeneros = defineGenero();
            generos.setText(listaGeneros);
        }

        private StringBuilder defineGenero() {
            GeneroDAO dao = new GeneroDAO();
            StringBuilder listaGeneros = new StringBuilder();
            listaGeneros.append("Generos: ");
            int[] genero = movie.getGenre_ids();
            int tamanho = genero.length;
            if (tamanho > 0){
                for (int value : genero) {
                    String retorno = dao.buscaGenero(value);
                    listaGeneros.append(retorno);
                }
            }else {
                listaGeneros.append("Dado indisponível !");
            }

            return listaGeneros;
        }
    }
}
