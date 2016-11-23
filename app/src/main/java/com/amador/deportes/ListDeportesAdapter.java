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

/**
 * Created by amador on 23/11/16.
 */

public class ListDeportesAdapter extends ArrayAdapter<Deportes> {

    private Context context;

    public ListDeportesAdapter(Context context) {
        super(context, R.layout.item_deportes_list, Repositorio.getInstance(context));
        this.context = context;
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

                    getItem(position).setSeguido(b);


                }
            });


            holder.imvDeporte.setImageResource(getItem(position).getImv());
            holder.checkSeguido.setChecked(getItem(position).isSeguido());
            holder.txvNombreDeporte.setText(getItem(position).getNombre());
            view.setVisibility(getItem(position).isVisible() ? View.VISIBLE : View.GONE);

            if(getItem(position).isVisible()){

                view.setVisibility(View.VISIBLE);

            }else{

                view.setVisibility(View.GONE);
            }


        return view;
    }

    class DeportesHolder{


        ImageView imvDeporte;
        TextView txvNombreDeporte;
        CheckBox checkSeguido;
    }
}
