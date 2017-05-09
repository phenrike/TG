package com.mainp.paulosantos.mainp;

/**
 * Created by Paulo Santos on 24/01/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class tab3Notifica extends Fragment {

    public TextView tvMensagem;
    public ListView lvNotificacoes;
    public List<Notificacao> listaDeNotificacoes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab3notifica, container, false);

        final Usuario usuario = (Usuario) getActivity().getIntent().getSerializableExtra("usuario");

        tvMensagem = (TextView) view.findViewById(R.id.tvMensagem);
        lvNotificacoes = (ListView) view.findViewById(R.id.lvNotificacoes);

        Requisicao requisicao = new Requisicao();
        listaDeNotificacoes = requisicao.carregarNotificacoes(usuario);

        if (listaDeNotificacoes != null) {
            lvNotificacoes.setVisibility(view.VISIBLE);
            tvMensagem.setVisibility(view.INVISIBLE);
            ListaDeNotificacoesAdapter notificacoesAdapter = new ListaDeNotificacoesAdapter(getActivity(), listaDeNotificacoes);
            lvNotificacoes.setAdapter(notificacoesAdapter);
        } else {
            lvNotificacoes.setVisibility(view.INVISIBLE);
            tvMensagem.setVisibility(view.VISIBLE);
            tvMensagem.setText("Você não possui notificações.");
        }

        return view;
    }
}