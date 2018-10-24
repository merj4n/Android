package net.iesseveroochoa.germanbeldamolina.practica4.practica4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
