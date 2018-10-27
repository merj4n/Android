package net.iesseveroochoa.germanbeldamolina.practica4.practica4;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PoblacionesAdapter adaptadorLocalidadesValoradas;
    private ListView lsv_LocalidadesValoradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lsv_LocalidadesValoradas = findViewById(R.id.lv_lista_poblacion);

        ArrayList<Poblacion> lista = new ArrayList<>();
        adaptadorLocalidadesValoradas=new PoblacionesAdapter (this,R.layout.item_poblacion,lista);
        lista.add(new Poblacion("Alicante", "Elche", 4, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sed finibus ipsum. Curabitur non fermentum urna. Aliquam risus nunc, dapibus vitae commodo at, sollicitudin eget diam. Nunc consequat magna at fermentum maximus. Duis venenatis rutrum neque, mattis pulvinar purus vehicula "));
        //lista.add(new Poblacion("Alicante", "Alcoy", 2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sed finibus ipsum. Curabitur non fermentum urna. Aliquam risus nunc, dapibus vitae commodo at, sollicitudin eget diam. Nunc consequat magna at fermentum maximus. Duis venenatis rutrum neque, mattis pulvinar purus vehicula ullamcorper."));
        lsv_LocalidadesValoradas.setAdapter(adaptadorLocalidadesValoradas);
        //adaptadorLocalidadesValoradas.addPoblacion(new Poblacion("Alicante", "Alcoy", 2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sed finibus ipsum. Curabitur non fermentum urna. Aliquam risus nunc, dapibus vitae commodo at, sollicitudin eget diam. Nunc consequat magna at fermentum maximus. Duis venenatis rutrum neque, mattis pulvinar purus vehicula ullamcorper."));
            //adaptadorLocalidadesValoradas.delPoblacion(1);



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
                Intent intent_poblacion = new Intent(getApplicationContext(),PoblacionActivity.class);
                startActivity(intent_poblacion);
                //Toast.makeText(this,"Menu añadir",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_ordenar:
                Toast.makeText(this,"Menu ordenar",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_acercade:
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager, "tagAlerta");
                //Intent intent_acercade = new Intent(getApplicationContext(),DialogoAlerta.class);
                //startActivity(intent_acercade);
                //Toast.makeText(this,"Acerca de...",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
