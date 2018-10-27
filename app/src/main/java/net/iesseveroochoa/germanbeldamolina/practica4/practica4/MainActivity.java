package net.iesseveroochoa.germanbeldamolina.practica4.practica4;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PoblacionesAdapter adaptadorLocalidadesValoradas;
    private ListView lsv_LocalidadesValoradas;
    protected static final int REQUEST_CODE_POBLACION=1;
    private String rc_poblacion;
    private String rc_provincia;
    private RatingBar rc_valoracion;
    private EditText rc_comentario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lsv_LocalidadesValoradas = findViewById(R.id.lv_lista_poblacion);

        List<Poblacion> lista = new ArrayList<>();
        adaptadorLocalidadesValoradas=new PoblacionesAdapter (this,R.layout.item_poblacion,lista);
        lsv_LocalidadesValoradas.setAdapter(adaptadorLocalidadesValoradas);
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
     * Recojo los valor de poblacion seleccionados en el activity_poblacion y los agrego a la lista.
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
            }
        }
    }

}
