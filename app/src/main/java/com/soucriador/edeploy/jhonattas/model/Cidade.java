package com.soucriador.edeploy.jhonattas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Cidade extends RealmObject {

    @SerializedName("Nome")
    private String nome;
    @SerializedName("Estado")
    private String estado;
    
    public Cidade(){
    }

    public Cidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
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

    @Override
    public String toString() {
        return "Cidade{" +
                    "nome='" + nome + '\'' +
                    ", estado='" + estado + '\''+
                '}';
    }
}
