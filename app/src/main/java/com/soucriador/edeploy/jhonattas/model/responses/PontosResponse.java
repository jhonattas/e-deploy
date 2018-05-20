package com.soucriador.edeploy.jhonattas.model.responses;

public class PontosResponse {

    private String pontos;    

    public PontosResponse(){
    }

    public PontosResponse(String pontos) {
        this.pontos = pontos;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }
}