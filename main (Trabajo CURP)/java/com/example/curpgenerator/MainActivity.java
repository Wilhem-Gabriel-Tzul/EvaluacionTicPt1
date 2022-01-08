package com.example.curpgenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText cNombre;
    EditText cApellidop;
    EditText cApellidom;
    TextView tcurp;
    private RadioGroup grupo;
    private Spinner spinner1;
    Spinner SpYear, SpMonth, SpDay;
    TextView txtYear, txtMonth, txtDay;
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
        cNombre = (EditText) findViewById(R.id.cNombre);
        cApellidop = (EditText) findViewById(R.id.cApellidop);
        cApellidom = (EditText) findViewById(R.id.cApellidom);
        tcurp = (TextView) findViewById(R.id.curp);
        grupo = (RadioGroup) findViewById(R.id.opciones);
        spinner1=(Spinner)findViewById(R.id.lista);
        SpYear=(Spinner)findViewById(R.id.spYear);
        SpMonth=(Spinner)findViewById(R.id.spMonth);
        SpDay=(Spinner)findViewById(R.id.spDay);
        txtYear=(TextView)findViewById(R.id.textViewYear);
        txtMonth=(TextView)findViewById(R.id.textViewMonth);
        txtDay=(TextView)findViewById(R.id.textViewDay);

        String[] estados = {"AGUASCALIENTES","BAJA CALIFORNIA","BAJA CALIFORNIA SUR","CAMPECHE","COAHUILA"
                ,"COLIMA","CHIAPAS","CHIHUAHUA","DISTRITO FEDERAL","DURANGO","GUANAJUATO","GUERRERO","HIDALGO",
                "JALISCO", "MÉXICO","MICHOACÁN","MORELOS","NAYARIT","NUEVO LEÓN","OAXACA","PUEBLA","QUERÉTARO",
                "QUINTANA ROO", "SAN LUIS POTOSÍ","SINALOA","SONORA","TABASCO","TAMAULIPAS","TLAXCALA","VERACRUZ",
                "YUCATÁN","ZACATECAS","NACIDO EN EL EXTRANJERO"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                ( this,android.R.layout.simple_dropdown_item_1line,estados );
        spinner1.setAdapter(adapter);
        for(int i = 2022; i>=1940;i--)
        {
            arrayListYear.add(i+"");
        }
        Adaptador=new ArrayAdapter(getBaseContext(),
                android.R.layout.simple_spinner_item, arrayListYear);
        SpYear = (Spinner)findViewById(R.id.spYear);
        SpYear.setAdapter(Adaptador);

        for(int i = 12; i>0;i--)
        {
            arrayListMonth.add(i+"");
        }
        Adaptador2=new ArrayAdapter(getBaseContext(),
                android.R.layout.simple_spinner_item, arrayListMonth);
        SpMonth = (Spinner)findViewById(R.id.spMonth);
        SpMonth.setAdapter(Adaptador2);

        for(int i = 31; i>0;i--)
        {
            arrayListDay.add(i+"");
        }
        Adaptador3=new ArrayAdapter(getBaseContext(),

                android.R.layout.simple_spinner_item, arrayListDay);
        SpDay = (Spinner)findViewById(R.id.spDay);
        SpDay.setAdapter(Adaptador3);
    }
    //Needs Checking

    public void GenCURP(View view) {
        try {
            //Variable a la que se asignara CURP
            String curp = "";
            //variable destinada a la primera vowel del apellido paterno
            String vowel = "";
            //almacena seleccion del spinner
            String seleccion = spinner1.getSelectedItem().toString();
            //anexo de la primera letra del EditText apellido paterno
            curp += cApellidop.getText().toString().substring(0,
                    1).toUpperCase();

            //ciclo que recorre la cadena de apellido paterno en busca de la primera vowel
            for (int i = 1; i < (cApellidop.getText().toString().length() - 1);
                 i++) {
                //extrae cada una de las letras
                vowel = cApellidop.getText().toString().substring(i, i +
                        1).toUpperCase();
                //La condicion identifica si el valor de la variable "vowel" es una vowel
                if (vowel.equals("A") || vowel.equals("E") || vowel.equals("I")
                        || vowel.equals("O") || vowel.equals("U")) {
                    //Si la condicion n es verdadera la vowel es asignada a la variable CURP
                    curp += vowel;
                    //El ciclo se rompe
                    break;
                }
            }

            //Anexo de la primer letra del EditText apellido materno
            curp += cApellidom.getText().toString().substring(0,
                    1).toUpperCase();

            //Anexo de la primer letra del EdiText nombre
            curp += cNombre.getText().toString().substring(0, 1).toUpperCase();

            txtYear.setText(SpYear.getSelectedItem() + "");
            txtMonth.setText(SpMonth.getSelectedItem() + "");
            txtDay.setText(SpDay.getSelectedItem() + "");

            //Anexamos los digitos del año
            curp+=Integer.parseInt(txtYear.getText().toString().substring(2,3));
            curp+=Integer.parseInt(txtYear.getText().toString().substring(3,4));

            //Anexamos el Month seleccionado
            if (txtMonth.getText().toString().length() < 2) {
                //Si es verdadero anexa un 0 al inicio
                //Y despues se agrega a la variable CURP
                curp += "0" + txtMonth.getText().toString();
            } else {
                //Si es falso se agrega a la variable CURP
                curp += txtMonth.getText().toString();
            }

            //Anexamos el Day seleccionado
            if (txtDay.getText().toString().length() < 2) {
                //Si es verdadero anexa un 0 al inicio
                //Y despues se agrega a la variable CURP
                curp += "0" + txtDay.getText().toString();
            } else {
                //Si es falso se agrega a la variable CURP
                curp += txtDay.getText().toString();
            }

            if (grupo.getCheckedRadioButtonId() == R.id.radio_hombre) {
                curp += "H";
            } else {
                curp += "M";
            }

            if (seleccion.equals("AGUASCALIENTES")) {
                curp += "AS";
            } else if (seleccion.equals("BAJA CALIFORNIA")) {
                curp += "BC";
            } else if (seleccion.equals("BAJA CALIFORNIA SUR")) {
                curp += "BS";
            } else if (seleccion.equals("CAMPECHE")) {
                curp += "CC";
            } else if (seleccion.equals("COAHUILA")) {
                curp += "CL";
            } else if (seleccion.equals("COLIMA")) {
                curp += "CM";
            } else if (seleccion.equals("CHIAPAS")) {
                curp += "CS";
            } else if (seleccion.equals("CHIHUAHUA")) {
                curp += "CH";
            } else if (seleccion.equals("DISTRITO FEDERAL")) {
                curp += "DF";
            } else if (seleccion.equals("DURANGO")) {
                curp += "DG";
            } else if (seleccion.equals("GUANAJUATO")) {
                curp += "GT";
            } else if (seleccion.equals("GUERRERO")) {
                curp += "GR";
            } else if (seleccion.equals("HIDALGO")) {
                curp += "HG";
            } else if (seleccion.equals("JALISCO")) {
                curp += "JC";
            } else if (seleccion.equals("MÉXICO")) {
                curp += "MC";
            } else if (seleccion.equals("MICHOACÁN")) {
                curp += "MN";
            } else if (seleccion.equals("MORELOS")) {
                curp += "MS";
            } else if (seleccion.equals("NAYARIT")) {
                curp += "NT";
            } else if (seleccion.equals("NUEVO LEÓN")) {
                curp += "NL";
            } else if (seleccion.equals("OAXACA")) {
                curp += "OC";
            } else if (seleccion.equals("PUEBLA")) {
                curp += "PL";
            } else if (seleccion.equals("QUERÉTARO")) {
                curp += "QT";
            } else if (seleccion.equals("QUINTANA ROO")) {
                curp += "QR";
            } else if (seleccion.equals("SAN LUIS POTOSÍ")) {
                curp += "SP";
            } else if (seleccion.equals("SINALOA")) {
                curp += "SL";
            } else if (seleccion.equals("SONORA")) {
                curp += "SR";
            } else if (seleccion.equals("TABASCO")) {
                curp += "TC";
            } else if (seleccion.equals("TAMAULIPAS")) {
                curp += "TS";
            } else if (seleccion.equals("TLAXCALA")) {
                curp += "TL";
            } else if (seleccion.equals("VERACRUZ")) {
                curp += "VZ";
            } else if (seleccion.equals("YUCATÁN")) {
                curp += "YN";
            } else if (seleccion.equals("ZACATECAS")) {
                curp += "ZS";
            } else if (seleccion.equals("NACIDO EN EL EXTRANJERO")) {
                curp += "NE";
            }

            for (int i = 1; i < (cApellidop.getText().toString().length() - 1);
                 i++) {
                vowel = cApellidop.getText().toString().substring(i, i +
                        1).toUpperCase();
                if (vowel.equals("Ñ")) {
                    curp += "X";
                } else if (vowel.equals("B") || vowel.equals("C") ||
                        vowel.equals("D") || vowel.equals("F")
                        || vowel.equals("G") || vowel.equals("H") ||
                        vowel.equals("J") || vowel.equals("K")
                        || vowel.equals("L") || vowel.equals("M") ||
                        vowel.equals("N") || vowel.equals("P")
                        || vowel.equals("Q") || vowel.equals("R") ||
                        vowel.equals("S") || vowel.equals("T")
                        || vowel.equals("V") || vowel.equals("W") ||
                        vowel.equals("X") || vowel.equals("Y")
                        || vowel.equals("Z")) {
                    curp += vowel;
                    break;
                }
            }

            for (int i = 1; i < (cApellidom.getText().toString().length() - 1);
                 i++) {
                vowel = cApellidom.getText().toString().substring(i, i +
                        1).toUpperCase();
                if (vowel.equals("Ñ")) {
                    curp += "X";
                } else if (vowel.equals("B") || vowel.equals("C") ||
                        vowel.equals("D")
                        || vowel.equals("F") || vowel.equals("G") ||
                        vowel.equals("H")
                        || vowel.equals("J") || vowel.equals("K") ||
                        vowel.equals("L")
                        || vowel.equals("M") || vowel.equals("N") ||
                        vowel.equals("P")
                        || vowel.equals("Q") || vowel.equals("R") ||
                        vowel.equals("S")
                        || vowel.equals("T") || vowel.equals("V") ||
                        vowel.equals("W")
                        || vowel.equals("X") || vowel.equals("Y") ||
                        vowel.equals("Z")) {
                    curp += vowel;
                    break;
                }
            }

            for (int i = 1; i < (cNombre.getText().toString().length() - 1); i++)
            {
                vowel = cNombre.getText().toString().substring(i, i +
                        1).toUpperCase();
                if (vowel.equals("Ñ")) {
                    curp += "X";
                } else if (vowel.equals("B") || vowel.equals("C") ||
                        vowel.equals("D")
                        || vowel.equals("F") || vowel.equals("G") ||
                        vowel.equals("H")
                        || vowel.equals("J") || vowel.equals("K") ||
                        vowel.equals("L")
                        || vowel.equals("M") || vowel.equals("N") ||
                        vowel.equals("P")
                        || vowel.equals("Q") || vowel.equals("R") ||
                        vowel.equals("S")
                        || vowel.equals("T") || vowel.equals("V") ||
                        vowel.equals("W")
                        || vowel.equals("X") || vowel.equals("Y") ||
                        vowel.equals("Z")) {
                    curp += vowel;
                    break;
                }
            }
            //Homoclave
            Random rnd = new Random();
            int xtend = rnd.nextInt(90) + 10;
            curp += xtend;

            tcurp.setText("CURP:" + curp);
        } catch (Exception e) {
            Toast.makeText(this, "Llena los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void erase(View view) {
        cNombre.setText("");
        cApellidop.setText("");
        cApellidom.setText("");
        txtYear.setText("");
        txtMonth.setText("");
        txtDay.setText("");
        tcurp.setText("CURP:");
    }
}