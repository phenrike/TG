package com.mainp.paulosantos.mainp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class ListaDeNotificacoesAdapter extends ArrayAdapter<Notificacao> {

    private Context context;
    private List<Notificacao> listaDeNotificacoes = null;
    private Notificacao notificacao;

    public ListaDeNotificacoesAdapter(Context context, List<Notificacao> listaDeNotificacoes) {
        super(context, 0, listaDeNotificacoes);
        this.listaDeNotificacoes = listaDeNotificacoes;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        notificacao = listaDeNotificacoes.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.lista_de_notificacoes, null);

        //Quando o usuário tiver foto
        //ImageView ivFoto = (ImageView) view.findViewById(R.id.ivFoto);
        //ivFoto.setImageResource(usuario.getImagem());

        TextView tvTexto = (TextView) view.findViewById(R.id.tvTexto);
        tvTexto.setText(notificacao.getMensagem());

        Button btAcao = (Button) view.findViewById(R.id.btAcao);
        btAcao.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Cria tela de perfil
                Intent perfilOutroUsuActivity = new Intent(context, PerfilOutroUsuActivity.class);

                //Abre a tela de perfil passando o usuário
                /* Aqui a variável "receptor" recebe o "getEmissor()", pois o receptor do compartilhamento
                   será o emissor da notificação */
                perfilOutroUsuActivity.putExtra("receptor", (Serializable) notificacao.getEmissor());

                /* Aqui a variável "receptor" recebe o "getEmissor()", pois o emissor do compartilhamento
                   será o receptor da notificação */
                perfilOutroUsuActivity.putExtra("emissor", (Serializable) notificacao.getReceptor());

                context.startActivity(perfilOutroUsuActivity);
            }
        });

        return view;
    }
}