package com.sandev.ceabs.model;

import java.util.List;

public class Resposta {
    private int total_results;
    private List<Movie> results;

    public Resposta() {
    }

    public List<Movie> getResults() {
        return results;
    }

    public String itensRetornados(){
        return "Filmes encontrados: "+total_results;
    }
}
