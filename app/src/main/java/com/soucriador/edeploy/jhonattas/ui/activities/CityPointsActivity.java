package com.soucriador.edeploy.jhonattas.ui.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.soucriador.edeploy.jhonattas.R;
import com.soucriador.edeploy.jhonattas.model.params.BuscaPontosParams;
import com.soucriador.edeploy.jhonattas.model.responses.PontosResponse;
import com.soucriador.edeploy.jhonattas.rest.ServerClient;
import com.soucriador.edeploy.jhonattas.rest.ServerInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityPointsActivity extends AppCompatActivity {

    private static final String TAG = CityPointsActivity.class.getSimpleName();

    String cidade;
    String estado;
    String pontos;

    TextView tvCidade;
    TextView tvPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();

        cidade = b.getString("city");
        estado = b.getString("state");

        setContentView(R.layout.activity_city_points);

        findComponents();
    }

    void findComponents() {
        tvCidade = findViewById(R.id.tvCity);
        tvPontos = findViewById(R.id.tvPoints);

        String description =
                getString(R.string.the_punctuation_of) + " "
                + cidade
                + " " +
                getString(R.string.is);
        tvCidade.setText(description);
        setTitle(estado);
        loadPoints();
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    void loadPoints(){
        BuscaPontosParams bpp = new BuscaPontosParams(cidade, estado);

        ServerInterface serverService = ServerClient.getClient().create(ServerInterface.class);
        Call<Integer> callPoints = serverService.buscaPontos(bpp);
        callPoints.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                pontos = String.valueOf(response.body());
                tvPontos.setText(pontos);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
    }

}
