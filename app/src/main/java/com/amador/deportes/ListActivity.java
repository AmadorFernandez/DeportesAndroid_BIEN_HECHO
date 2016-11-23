package com.amador.deportes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {


    private Button btnAceptar;
    private ListView listView;
    private ListDeportesAdapter adapter;
    private Preferencias p;
    private boolean[] seguidos;
    private static final String KEY = "KEY";

    /**
     * Carga las preferencias si existen y enlaza el boton con el evento OnClick
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        p = new Preferencias(ListActivity.this);
        p.cargar();
        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        listView = (ListView)findViewById(R.id.list);
        adapter = new ListDeportesAdapter(ListActivity.this);
        listView.setDivider(null);
        listView.setAdapter(adapter);


        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.salvar();
                Toast.makeText(ListActivity.this, getString(R.string.save), Toast.LENGTH_LONG).show();
            }
        });

    }


    /**
     * Filtra los deportes que empiezen por el caracter indicado por el usuario
     * mediante un AlertDialog.Builder personalizado.
     * */
    private void filtrado(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
        View vi = getLayoutInflater().inflate(R.layout.text_dialog_filter, null);
        final EditText edt = (EditText)vi.findViewById(R.id.edtDialog);
        builder.setView(edt);
        builder.setTitle(getString(R.string.filter_title));
        builder.setMessage(getString(R.string.filter_message));

        Dialog dialog = builder.setPositiveButton(getString(R.string.filter_text_btn), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                    String aux = edt.getText().toString();

                    for(int j = 0; j < adapter.getCount(); j++) {

                        if (!adapter.getItem(j).getNombre().toUpperCase().startsWith(aux.toUpperCase())) {

                            adapter.getItem(j).setVisible(false);

                        }else{

                            adapter.getItem(j).setVisible(true);
                        }
                    }

            }
        }).create();

        ViewGroup parent = (ViewGroup)vi;
        parent.removeAllViews();
        dialog.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        seguidos = new boolean[adapter.getCount()];

        for(int i = 0; i < seguidos.length; i++){

            seguidos[i] = adapter.getItem(i).isSeguido();
        }
        outState.putBooleanArray(KEY, seguidos);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        seguidos = savedInstanceState.getBooleanArray(KEY);

        for(int i = 0; i < seguidos.length; i++){

            adapter.getItem(i).setSeguido(seguidos[i]);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_filter:
                filtrado();
                adapter.notifyDataSetChanged();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
