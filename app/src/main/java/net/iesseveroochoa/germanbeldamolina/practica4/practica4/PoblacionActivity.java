package net.iesseveroochoa.germanbeldamolina.practica4.practica4;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PoblacionActivity extends AppCompatActivity {
    TypedArray arrayLocalidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion);

        // Implementación de la selección de provincia
        arrayLocalidades = getResources().obtainTypedArray( R.array.array_provincia_a_localidades);
        Spinner spn_provincias = findViewById(R.id.spn_provincia);
        final Spinner spn_poblacion = findViewById(R.id.spn_poblacion);
        spn_provincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                CharSequence[] localidades = arrayLocalidades.getTextArray(pos);
                //Construcción del "adaptador" para el Spinner de poblaciones
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(PoblacionActivity.this, android.R.layout.simple_spinner_item, localidades);

                //Se carga el tipo de vista para el adaptador
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //Se aplica el adaptador al Spinner de localidades
                spn_poblacion.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
