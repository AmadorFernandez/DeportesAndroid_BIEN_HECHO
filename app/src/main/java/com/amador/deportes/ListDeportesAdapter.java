package com.amador.deportes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amador on 23/11/16.
 */

public class ListDeportesAdapter extends ArrayAdapter<Deportes> {

    private Context context;
    private ArrayList<Deportes> data;

    public ListDeportesAdapter(Context context) {
        super(context, R.layout.item_deportes_list, new ArrayList<Deportes>(Repositorio.getInstance(context)));
        this.context = context;
        this.data = new ArrayList<Deportes>(Repositorio.getInstance(context));

    }

    /**
     * Metodo que es llamado cada vez que se añade un nuevo elemento a la lista
     * o cuando la vista es notificada de que hay cambios
     * */
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        DeportesHolder holder;

            if (view == null) {

                LayoutInflater inflater = LayoutInflater.from(context);
                view = inflater.inflate(R.layout.item_deportes_list, null);
                holder = new DeportesHolder();
                holder.imvDeporte = (ImageView) view.findViewById(R.id.item_imv);
                holder.txvNombreDeporte = (TextView) view.findViewById(R.id.txvNombreDeporte);
                holder.checkSeguido = (CheckBox) view.findViewById(R.id.item_check);


                view.setTag(holder);


            } else {

                holder = (DeportesHolder) view.getTag();
            }


            holder.checkSeguido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    data.get(position).setSeguido(b);


                }
            });


            holder.imvDeporte.setImageResource(data.get(position).getImv());
            holder.checkSeguido.setChecked(data.get(position).isSeguido());
            holder.txvNombreDeporte.setText(data.get(position).getNombre());

        return view;
    }

    public  void filterData(String caracter){

        data.clear();
        Repositorio instancia = Repositorio.getInstance(this.context);

        for(int j = 0; j < instancia.size(); j++) {

            if (instancia.get(j).getNombre().toUpperCase().startsWith(caracter.toUpperCase())) {

                data.add(instancia.get(j));

            }
        }
        clear();
        addAll(data);
        notifyDataSetChanged();
    }


    class DeportesHolder{


        ImageView imvDeporte;
        TextView txvNombreDeporte;
        CheckBox checkSeguido;
    }
}
