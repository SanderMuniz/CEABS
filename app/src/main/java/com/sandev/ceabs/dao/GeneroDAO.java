package com.sandev.ceabs.dao;

import com.sandev.ceabs.model.Genero;

import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    private final static List<Genero> generos = new ArrayList<>();

    public void setarGeneros(List<Genero> list) {
        generos.clear();
        generos.addAll(list);
    }

    public String buscaGenero(int id) {
        for (Genero genero : generos) {
            if (genero.getId() == id) {
                return genero.getName();
            }
        }
        return null;
    }

    public List<Genero> todos() {
        return new ArrayList<>(generos);
    }
}
