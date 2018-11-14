package com.example.jaime.datospersonales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre;
    EditText edtApellidos;
    EditText edtEdad;
    RadioGroup grupoGenero;
    RadioButton btnHombre;
    RadioButton btnMujer;
    Spinner spinnerLista;
    Switch switchHijos;
    String generoSeleccionado = "";
    String edadResultado;
    String seleccionado = "";
    Button btnGenerar;
    ImageButton ImgX;
    String[] resultado;
    TextView txtResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre  = findViewById(R.id.edtNombre);
        edtApellidos  = findViewById(R.id.edtApellidos);
        edtEdad = findViewById(R.id.edtEdad);
        btnGenerar = findViewById(R.id.btnGenerar);
        ImgX = findViewById(R.id.ImgX);
        txtResultado = findViewById(R.id.txtResultado);
        spinnerLista  =findViewById(R.id.spinnerLista);
        switchHijos = findViewById(R.id.switchHijos);
        grupoGenero = findViewById(R.id.grupoGenero);
        btnHombre = findViewById(R.id.btnHombre);
        btnMujer  = findViewById(R.id.btnMujer);

        String[] resultado = new String[] {getResources().getString(R.string.Vacio),getResources().getString(R.string.Casado),getResources().getString(R.string.Separado),getResources().getString(R.string.Viudo),getResources().getString(R.string.Otro)};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resultado);
        spinnerLista.setAdapter(adaptador);

        grupoGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.btnHombre) generoSeleccionado = getResources().getString(R.string.btnHombre);
                if (checkedId == R.id.btnMujer) generoSeleccionado = getResources().getString(R.string.btnMujer);
            }
        });
        switchHijos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchHijos.isChecked() == true)
                {
                    seleccionado = getResources().getString(R.string.TieneHijos);
                }
                if (switchHijos.isChecked() == false)
                {
                    seleccionado = getResources().getString(R.string.NoTieneHijos);
                }
            }
        });
        spinnerLista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerLista.getSelectedItemPosition();
                spinnerLista.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        ImgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNombre.setText("");
                edtApellidos.setText("");
                spinnerLista.setSelection(0);
                edtEdad.setText("");
                switchHijos.setChecked(false);
                btnHombre.setChecked(false);
                btnMujer.setChecked(false);


                txtResultado.setText("");
            }
        });
        btnGenerar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (edtNombre.getText().toString().isEmpty()) {
                    txtResultado.setText(getResources().getString(R.string.FaltaNombre));
                    txtResultado.setTextColor(getColor(R.color.btnMujer));
                }
                else{
                    if (edtApellidos.getText().toString().isEmpty()) {
                        txtResultado.setText(getResources().getString(R.string.FaltaApellidos));
                        txtResultado.setTextColor(getColor(R.color.btnMujer));
                    } else {
                        if (edtEdad.getText().toString().isEmpty()) {
                            txtResultado.setText(getResources().getString(R.string.FaltaEdad));
                            txtResultado.setTextColor(getColor(R.color.btnMujer));
                        } else {
                            if (Integer.parseInt(edtEdad.getText().toString()) >= 18) {
                                edadResultado = getResources().getString(R.string.MayorEdad);
                            } else {
                                edadResultado = getResources().getString(R.string.MenorEdad);
                            }
                            txtResultado.setText(edtApellidos.getText().toString() + " " + edtNombre.getText().toString() + "," + edadResultado + "," + generoSeleccionado + " " + spinnerLista.getSelectedItem().toString() + " y " + seleccionado );
                            txtResultado.setTextColor(getColor(R.color.negro));
                        }
                    }

                }


            }
        });
    }}
