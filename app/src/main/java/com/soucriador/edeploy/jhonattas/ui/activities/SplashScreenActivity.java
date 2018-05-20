package com.soucriador.edeploy.jhonattas.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.soucriador.edeploy.jhonattas.R;
import com.soucriador.edeploy.jhonattas.model.Cidade;
import com.soucriador.edeploy.jhonattas.rest.ServerClient;
import com.soucriador.edeploy.jhonattas.rest.ServerInterface;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// esta activity vai verificar a conexao com o servidor da aplicacao
// retornar a lista de cidades do sistema e persistir a mesma temporariamente
public class SplashScreenActivity extends AppCompatActivity implements Runnable {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private Long delayTime = 500L;
    private List<Cidade> cidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler h = new Handler();
        h.postDelayed(this, delayTime);
    }

    @Override
    public void run() {
        clearDatabase();
        carregaCidades();
    }

    private void startMainActivity() {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
        finish();
    }

    private void carregaCidades() {
        ServerInterface serverService = ServerClient.getClient().create(ServerInterface.class);

        Call<List<Cidade>> cidadesCall = serverService.buscaTodasCidades();
        cidadesCall.enqueue(new Callback<List<Cidade>>() {
            @Override
            public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
                cidades = response.body();
                saveCities();
            }

            @Override
            public void onFailure(Call<List<Cidade>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void saveCities() {
        try(Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmList<Cidade> listaCidades = new RealmList<>();
                    listaCidades.addAll(cidades);
                    realm.insert(listaCidades);
                    startMainActivity();
                }
            });
        } finally {
            Log.e(TAG, "inseridas " + cidades.size() + " cidades");
        }
    }

    private void clearDatabase() {
        try(Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.deleteAll();
                }
            });
        }
    }
}
