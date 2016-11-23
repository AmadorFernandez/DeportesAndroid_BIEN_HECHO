package com.amador.deportes;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;

/**
 * Clase singleton para el repositorio de deportes
 */

public class Repositorio extends ArrayList<Deportes> {

    private static Repositorio instancia;
    private Context context;

    public static Repositorio getInstance(Context context){

        if(instancia == null){

            instancia = new Repositorio(context);
        }

        return instancia;
    }

    private Repositorio(Context context){

        this.context = context;
        add(new Deportes(R.drawable.icon_american_football, this.context.getString(R.string.rugby), false, true));
        add(new Deportes(R.drawable.icon_baseball, this.context.getString(R.string.bseball), false, true));
        add(new Deportes(R.drawable.icon_boxing, this.context.getString(R.string.boxin), false, true));
        add(new Deportes(R.drawable.icon_chess, this.context.getString(R.string.chess), false, true));
        add(new Deportes(R.drawable.icon_cricket, this.context.getString(R.string.cricket), false, true));
        add(new Deportes(R.drawable.icon_cycling, this.context.getString(R.string.cycling), false, true));
        add(new Deportes(R.drawable.icon_darts, this.context.getString(R.string.darts), false, true));
        add(new Deportes(R.drawable.icon_golf, this.context.getString(R.string.golf), false, true));

    }
}
