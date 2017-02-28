package com.mainp.paulosantos.mainp;

/**
 * Created by Paulo Santos on 24/01/2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class tab2Busca extends Fragment {

    Spinner redes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2busca, container, false);

        redes = (Spinner) rootView.findViewById(R.id.spRedes);
        ImageButton btbuscar = (ImageButton) rootView.findViewById(R.id.btBuscar);
        ArrayAdapter aa = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.redes_sociais,android.R.layout.simple_spinner_item);
        redes.setAdapter(aa);
        //btbuscar.setOnClickListener((View.OnClickListener) getView());
        return rootView;
    }

    /*public void onClick(View v) {
        int item = redes.getSelectedItemPosition();
        Toast.makeText(getActivity().getApplicationContext(), "Item escolhido" + Integer.toString(item), Toast.LENGTH_SHORT).show();
    }*/
}
