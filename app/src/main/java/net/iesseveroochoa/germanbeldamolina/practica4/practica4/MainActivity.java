package net.iesseveroochoa.germanbeldamolina.practica4.practica4;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogoBorrar.NoticeDialogListener {

    protected static final int REQUEST_CODE_POBLACION=1;
    protected static final int REQUEST_CODE_EDIT_POBLACION=2;
    protected static final String EXTRA_POBLACION_EDITAR = "com.teammarro.german.android.poblacion.editar";

    private PoblacionesAdapter adaptadorLocalidadesValoradas;
    private ListView lsv_LocalidadesValoradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Identifico cual es el listView de las localidades
        lsv_LocalidadesValoradas = findViewById(R.id.lv_lista_poblacion);
        // Creo un ArrrayList que contendra las poblaciones
        List<Poblacion> lista = new ArrayList<>();
        //Nuevo adaptador de poblaciones para el ArrayList anterior
        adaptadorLocalidadesValoradas=new PoblacionesAdapter (this,R.layout.item_poblacion,lista);
        //Ahora el ListView tiene al adaptador que hemos creado anteriormente
        lsv_LocalidadesValoradas.setAdapter(adaptadorLocalidadesValoradas);

        /**
         * Editamos el item de la poblacion sobre la que hemos pulsado
         */

        lsv_LocalidadesValoradas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,PoblacionActivity.class);
                intent.putExtra(EXTRA_POBLACION_EDITAR,adaptadorLocalidadesValoradas.getItem(position));
                startActivityForResult(intent,REQUEST_CODE_EDIT_POBLACION);
            }
        });

        lsv_LocalidadesValoradas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoBorrar dialogo = new DialogoBorrar();
                Bundle args = new Bundle();
                args.putInt("pos", i);
                dialogo.setArguments(args);
                dialogo.show(fragmentManager, getString(R.string.borrar));
                return true;
            }
        });


    }

    /**
     * Metodo que crea el menu de añadir, ordenar y acerca de...
     * @param menu
     * @return creación del menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.it_menu_add:
                Intent intent_poblacion = new Intent(MainActivity.this,PoblacionActivity.class);
                startActivityForResult(intent_poblacion,REQUEST_CODE_POBLACION);
                return true;
            case R.id.it_ordenar:
                Toast.makeText(this,"Menu ordenar",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_acercade:
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager, getString(R.string.alerta));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Recojo los valores de poblacion seleccionados en el activity_poblacion y los agrego a la lista.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CODE_POBLACION){
                adaptadorLocalidadesValoradas.addPoblacion((Poblacion) data.getExtras().getParcelable(PoblacionActivity.EXTRA_POBLACION));
            }else if(requestCode == REQUEST_CODE_EDIT_POBLACION){
                adaptadorLocalidadesValoradas.editarLocalidad((Poblacion) data.getExtras().getParcelable(PoblacionActivity.EXTRA_POBLACION));
            }
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        adaptadorLocalidadesValoradas.delPoblacion(dialog.getArguments().getInt("pos"));
    }
}
