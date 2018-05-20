package com.soucriador.edeploy.jhonattas.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.soucriador.edeploy.jhonattas.R;
import com.soucriador.edeploy.jhonattas.model.Cidade;
import com.soucriador.edeploy.jhonattas.ui.interfaces.OnFragmentInteractionListener;
import io.realm.RealmResults;

public class CidadeDataAdapter extends RecyclerView.Adapter<CidadeDataAdapter.ViewHolder> {
    private RealmResults<Cidade> cidades;
    private OnFragmentInteractionListener listener;

    public CidadeDataAdapter(RealmResults<Cidade>cidades, OnFragmentInteractionListener listener) {
        this.cidades = cidades;
        this.listener = listener;
    }

    @Override
    public CidadeDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CidadeDataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.cidade = cidades.get(i);
        viewHolder.tv_nome.setText(viewHolder.cidade.getNome());
        viewHolder.tv_estado.setText(viewHolder.cidade.getEstado());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onFragmentInteraction(viewHolder.cidade);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cidades.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Cidade cidade;
        private TextView tv_nome,tv_estado;
        private View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_nome     = view.findViewById(R.id.tv_nome);
            tv_estado    = view.findViewById(R.id.tv_estado);
        }
    }

}