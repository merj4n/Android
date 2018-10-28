package net.iesseveroochoa.germanbeldamolina.practica4.practica4;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class PoblacionActivity extends AppCompatActivity {
    TypedArray arrayLocalidadesDeProvincias;
    CharSequence[] poblacion_provincia;
    private FloatingActionButton guardar;
    private Spinner spn_provincias;
    private Spinner spn_poblacion;
    private RatingBar valoracion;
    private EditText comentario;
    protected static final String EXTRA_POBLACION = "com.teammarro.german.android.poblacion";
    ArrayAdapter adapterProvincias;
    ArrayAdapter adapterPoblacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion);

        // Implementación de la selección de provincia
        arrayLocalidadesDeProvincias = getResources().obtainTypedArray( R.array.array_provincia_a_localidades);
        guardar = findViewById(R.id.bt_guardar);
        spn_provincias = findViewById(R.id.spn_provincia);
        spn_poblacion = findViewById(R.id.spn_poblacion);
        valoracion = findViewById(R.id.rb_addpoblacion);
        comentario = findViewById(R.id.et_comentario);

        adapterProvincias = ArrayAdapter.createFromResource(this,R.array.provincias,android.R.layout.simple_spinner_dropdown_item);
        spn_provincias.setAdapter(adapterProvincias);


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

        if (getIntent().getParcelableExtra(MainActivity.EXTRA_POBLACION_EDITAR) != null){
            Poblacion p = getIntent().getParcelableExtra(MainActivity.EXTRA_POBLACION_EDITAR);
            int posicionProvincia = adapterProvincias.getPosition(p.getProvincia());
            spn_provincias.setSelection(posicionProvincia);
            comentario.setText(p.getComentarios());
            valoracion.setRating(p.getValoracion());

            //Cambiando las posiciones de los spiner y anulando su uso.
            updateSpinnerPoblacion(posicionProvincia);
            spn_poblacion.setSelection(adapterPoblacion.getPosition(p.getLocalidad()));
            spn_provincias.setClickable(false);
            spn_provincias.setEnabled(false);
            spn_poblacion.setClickable(false);
            spn_poblacion.setEnabled(false);
            setTitle(getResources().getString(R.string.modifica_poblacion)+" "+p.getLocalidad());
        } else {
            spn_provincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                    CharSequence[] localidades = arrayLocalidadesDeProvincias.getTextArray(pos);
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
    private void updateSpinnerPoblacion(int index) {
        poblacion_provincia = arrayLocalidadesDeProvincias.getTextArray(index);
        adapterPoblacion = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, poblacion_provincia);
        spn_poblacion.setAdapter(adapterPoblacion);
        adapterPoblacion.notifyDataSetChanged();
    }
}
