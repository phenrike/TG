package com.mainp.paulosantos.mainp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Paulo Santos on 25/03/2017.
 */
public class ListaDeUsuariosAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private List<Usuario> listaDeSsuarios = null;

    public ListaDeUsuariosAdapter(Context context, List<Usuario> listaDeSsuarios) {
        super(context, 0, listaDeSsuarios);
        this.listaDeSsuarios = listaDeSsuarios;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Usuario usuario = listaDeSsuarios.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.lista_de_usuarios, null);

        //Quando o usuário tiver foto
        //ImageView ivFoto = (ImageView) view.findViewById(R.id.ivFoto);
        //ivFoto.setImageResource(usuario.getImagem());

        TextView tvNomeUsuario = (TextView) view.findViewById(R.id.tvNomeUsuario);
        tvNomeUsuario.setText(usuario.getNome());

        return view;
    }
}