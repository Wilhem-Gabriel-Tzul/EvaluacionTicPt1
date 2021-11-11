package com.example.rfcgenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText Name;
    EditText apellidoP;
    EditText apellidoM;
    TextView trfc;
    Spinner SpYear, SpMonth, SpDay;
    TextView TvYear, TvMonth, TvDay;
    ArrayList arrayListYear = new ArrayList();
    ArrayAdapter Adaptador;
    ArrayList arrayListMonth = new ArrayList();
    ArrayAdapter Adaptador2;
    ArrayList arrayListDay = new ArrayList();
    ArrayAdapter Adaptador3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Name = (EditText) findViewById(R.id.Name);
        apellidoP = (EditText) findViewById(R.id.apellidoP);
        apellidoM = (EditText) findViewById(R.id.apellidoM);
        trfc = (TextView) findViewById(R.id.rfc);
        SpYear = (Spinner) findViewById(R.id.spinnerYear);
        SpMonth = (Spinner) findViewById(R.id.spinnerMonth);
        SpDay = (Spinner) findViewById(R.id.spinnerDay);
        TvYear = (TextView) findViewById(R.id.textViewYear);
        TvMonth = (TextView) findViewById(R.id.textViewMonth);
        TvDay = (TextView) findViewById(R.id.textViewDay);

        for (int i = 2020; i >= 1940; i--) {
            arrayListYear.add(i + "");
        }
        Adaptador = new ArrayAdapter(getBaseContext(),
                android.R.layout.simple_spinner_item, arrayListYear);
        SpYear = (Spinner) findViewById(R.id.spinnerYear);
        SpYear.setAdapter(Adaptador);
        for (int i = 12; i > 0; i--) {
            arrayListMonth.add(i + "");
        }
        Adaptador2 = new ArrayAdapter(getBaseContext(),
                android.R.layout.simple_spinner_item, arrayListMonth);
        SpMonth = (Spinner) findViewById(R.id.spinnerMonth);
        SpMonth.setAdapter(Adaptador2);
        for (int i = 31; i > 0; i--) {
            arrayListDay.add(i + "");
        }
        Adaptador3 = new ArrayAdapter(getBaseContext(),
                android.R.layout.simple_spinner_item, arrayListDay);
        SpDay = (Spinner) findViewById(R.id.spinnerDay);
        SpDay.setAdapter(Adaptador3);
    }

    public void miRFC(View view) {
        try {
            String rfc = "";
            String vocal = "";
            rfc += apellidoP.getText().toString().substring(0, 1).toUpperCase();
            for (int i = 1; i < (apellidoP.getText().toString().length() - 1); i++) {

                vocal = apellidoP.getText().toString().substring(i, i +
                        1).toUpperCase();

                if (vocal.equals("A") || vocal.equals("E") || vocal.equals("I") ||
                        vocal.equals("O") || vocal.equals("U")) {
                    //Si la condicion n es verdadera la vocal es asignada a la variable rfc
                    rfc += vocal;
                    //El ciclo se rompe
                    break;
                }
            }
            //Anexo de la primer letra del EditText apellido materno
            rfc += apellidoM.getText().toString().substring(0, 1).toUpperCase();
            //Anexo de la primer letra del nombre
            rfc += Name.getText().toString().substring(0, 1).toUpperCase();
            TvYear.setText(SpYear.getSelectedItem() + "");
            TvMonth.setText(SpMonth.getSelectedItem() + "");
            TvDay.setText(SpDay.getSelectedItem() + "");
            //Anexamos los digitos del año
            rfc += Integer.parseInt(TvYear.getText().toString().substring(2, 3));
            rfc += Integer.parseInt(TvYear.getText().toString().substring(3, 4));
            //Anexamos el Month seleccionado
            if (TvMonth.getText().toString().length() < 2) {
                rfc += "0" + TvMonth.getText().toString();
            } else {
                rfc += TvMonth.getText().toString();
            }

            //Anexamos el Day seleccionado
            if (TvDay.getText().toString().length() < 2) {
                rfc += "0" + TvDay.getText().toString();
            } else {
                rfc += TvDay.getText().toString();
            }
            trfc.setText("RFC:" + rfc);

            trfc.setText("RFC:" + rfc);
        } catch (Exception e) {
            Toast.makeText(this, "Llena los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void borrar(View view) {
        Name.setText("");
        apellidoP.setText("");
        apellidoM.setText("");
        TvYear.setText("");
        TvMonth.setText("");
        TvDay.setText("");
        trfc.setText("RFC:");
    }
}

