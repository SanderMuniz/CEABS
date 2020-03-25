package com.sandev.ceabs.model;

import android.text.TextUtils;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id;
    private String title;
    private String release_date;
    private String poster_path;
    private String overview;
    private int[] genre_ids;

    public Movie() {
    }

    public String getTitle() {
        if (TextUtils.isEmpty(title)) {
            return "Título: Dado indisponível !";
        }
        return "Título: " + title;
    }

    public String getRelease_date() {
        if (TextUtils.isEmpty(release_date)) {
            return "Lançamento: Dado indisponível !";
        } else {
            return "Lançamento: " + release_date;
        }
    }


    public String getPoster_path() {
        return poster_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public String getOverview() {
        if (overview.length() > 0) {
            return "Descrição: " + overview;
        } else {
            return "Descrição: Dado não disponível !";
        }
    }
}
