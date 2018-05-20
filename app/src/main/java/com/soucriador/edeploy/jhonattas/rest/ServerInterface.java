package com.soucriador.edeploy.jhonattas.rest;

import com.soucriador.edeploy.jhonattas.model.Cidade;
import com.soucriador.edeploy.jhonattas.model.params.BuscaPontosParams;
import com.soucriador.edeploy.jhonattas.model.responses.CidadeResponse;
import com.soucriador.edeploy.jhonattas.model.responses.PontosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface ServerInterface {

    @GET("rest/BuscaTodasCidades")
	Call<List<Cidade>> buscaTodasCidades();

	@POST("rest/BuscaPontos")
	Call<Integer> buscaPontos(@Body BuscaPontosParams params);
}
