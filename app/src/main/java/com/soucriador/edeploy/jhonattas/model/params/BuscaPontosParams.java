package com.soucriador.edeploy.jhonattas.model.params;

public class BuscaPontosParams {

    public String nome;
    public String estado;

    public BuscaPontosParams(String nome, String estado){
        setNome(nome);
        setEstado(estado);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
