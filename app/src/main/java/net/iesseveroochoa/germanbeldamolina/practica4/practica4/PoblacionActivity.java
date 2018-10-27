package net.iesseveroochoa.germanbeldamolina.practica4.practica4;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class PoblacionActivity extends AppCompatActivity {
    TypedArray arrayLocalidades;
    private FloatingActionButton guardar;
    private Spinner spn_provincias;
    private Spinner spn_poblacion;
    private RatingBar valoracion;
    private EditText comentario;
    protected static final String EXTRA_POBLACION = "com.teammarro.german.android.poblacion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion);

        // Implementación de la selección de provincia
        arrayLocalidades = getResources().obtainTypedArray( R.array.array_provincia_a_localidades);
        guardar = findViewById(R.id.bt_guardar);
        spn_provincias = findViewById(R.id.spn_provincia);
        spn_poblacion = findViewById(R.id.spn_poblacion);
        valoracion = findViewById(R.id.rb_addpoblacion);
        comentario = findViewById(R.id.et_comentario);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Poblacion p = new Poblacion(
                        spn_provincias.getSelectedItem().toString(),
                        spn_poblacion.getSelectedItem().toString(),
                        valoracion.getRating(),
                        comentario.getText().toString());
                intent.putExtra(EXTRA_POBLACION,p);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

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
