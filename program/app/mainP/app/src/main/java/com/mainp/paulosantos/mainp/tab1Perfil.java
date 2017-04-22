package com.mainp.paulosantos.mainp;

/**
 * Created by Paulo Santos on 24/01/2017.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.Serializable;

public class tab1Perfil extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab1perfil, container, false);

        //Esconde o teclado
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final Usuario usuario = (Usuario) getActivity().getIntent().getSerializableExtra("usuario");

        TextView tvNome = (TextView) rootView.findViewById(R.id.tvNome);
        TextView tvFace = (TextView) rootView.findViewById(R.id.tvFace);
        TextView tvWpp = (TextView) rootView.findViewById(R.id.tvWpp);
        TextView tvInsta = (TextView) rootView.findViewById(R.id.tvInsta);
        TextView tvSnap = (TextView) rootView.findViewById(R.id.tvSnap);
        TextView tvTwitter = (TextView) rootView.findViewById(R.id.tvTwitter);
        TextView tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);
        TextView tvLink = (TextView) rootView.findViewById(R.id.tvLink);
        Button btSair = (Button) rootView.findViewById(R.id.btSair);
        Button btEditar = (Button) rootView.findViewById(R.id.btEditar);

        if (usuario.getNOME() != null) {
            tvNome.setText(usuario.getNOME());
        }
        if (usuario.getFACE() != null) {
            tvFace.setText(usuario.getFACE());
        }
        if (usuario.getWPP() != null) {
            tvWpp.setText(usuario.getWPP());
        }
        if (usuario.getINSTA() != null) {
            tvInsta.setText(usuario.getINSTA());
        }
        if (usuario.getSNAP() != null) {
            tvSnap.setText(usuario.getSNAP());
        }
        if (usuario.getTWITTER() != null) {
            tvTwitter.setText(usuario.getTWITTER());
        }
        if (usuario.getEMAIL() != null) {
            tvEmail.setText(usuario.getEMAIL());
        }
        if (usuario.getLINK() != null) {
            tvLink.setText(usuario.getLINK());
        }

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deslogar();
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cria tela de edição de perfil
                Intent perfilEdicaoActivity = new Intent(getActivity(), PerfilEdicaoActivity.class);

                //Abre a tela de edição de perfil passando o usuário
                perfilEdicaoActivity.putExtra("usuario", usuario);
                getActivity().startActivity(perfilEdicaoActivity);
            }
        });

        return rootView;
    }

    public void deslogar() {
        //Cria tela login do app
        Intent loginActivity = new Intent(getActivity(), LoginActivity.class);

        Requisicao.tokenDeAcesso = "";

        //Vai para a tela de login
        getActivity().startActivity(loginActivity);
    }
}