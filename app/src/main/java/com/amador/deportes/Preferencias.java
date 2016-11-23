package com.amador.deportes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Clase para las preferencias
 */

public class Preferencias {

    private SharedPreferences preferences;
    private Context context;

    public Preferencias(Context context){

        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(this.context);

    }


    private SharedPreferences.Editor getEditor(){

        return preferences.edit();
    }

    /**
     * Salva en las preferencias los deportes que son seguidos por el usuario
     * usando el nombre del deporte como clave
     * */
    public void salvar(){

        SharedPreferences.Editor editor = getEditor();
        Repositorio repositorio = Repositorio.getInstance(this.context);

        for (int i = 0; i < repositorio.size(); i++){

            editor.putBoolean(repositorio.get(i).getNombre(), repositorio.get(i).isSeguido()).commit();
        }
    }

    /**
     * Carga las preferencias
     * */
    public void cargar(){


        Repositorio repositorio = Repositorio.getInstance(this.context);

        for (int i = 0; i < repositorio.size(); i++){

            repositorio.get(i).setSeguido(preferences.getBoolean(repositorio.get(i).getNombre(), false));
        }

    }


}
