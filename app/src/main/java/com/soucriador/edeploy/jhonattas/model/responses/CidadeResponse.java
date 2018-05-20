package com.soucriador.edeploy.jhonattas.model.responses;

import com.google.gson.annotations.SerializedName;
import com.soucriador.edeploy.jhonattas.model.Cidade;
import java.util.ArrayList;

public class CidadeResponse {

    private ArrayList<Cidade> results;

    public CidadeResponse(
        ArrayList<Cidade> results
    ){
        setResults(results);
    }

    public ArrayList<Cidade> getResults(){
        return results;
    }

    public void setResults(ArrayList<Cidade> results){
        this.results = results;
    }

}
