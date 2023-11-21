package com.suitep.calculadoratop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvOperacion, tvResultado;
    String operador = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOperacion = findViewById(R.id.tvOperacion);
        tvResultado = findViewById(R.id.tvResultado);

        assignId(R.id.botonAC);
        assignId(R.id.botonC);
        assignId(R.id.botonPunto);
        assignId(R.id.botonIgual);
        assignId(R.id.botonDivision);
        assignId(R.id.botonMultiplicacion);
        assignId(R.id.botonResta);
        assignId(R.id.botonSuma);
        assignId(R.id.boton9);
        assignId(R.id.boton8);
        assignId(R.id.boton7);
        assignId(R.id.boton6);
        assignId(R.id.boton5);
        assignId(R.id.boton4);
        assignId(R.id.boton3);
        assignId(R.id.boton2);
        assignId(R.id.boton1);
        assignId(R.id.boton0);
    }

    void assignId(int id) {
        Button btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public void onClick(View view){

            Button btn = (Button) view;
            String txtBtn = btn.getText().toString();
            String datosACalcular = tvOperacion.getText().toString();

            if (datosACalcular.equals("0")) {
                datosACalcular = "";
                tvOperacion.setText("");
            }

            switch (txtBtn){
                case "AC":
                    tvOperacion.setText("0");
                    tvResultado.setText("0");
                    return;

                case "C":
                    if (!datosACalcular.isEmpty())
                        datosACalcular = datosACalcular.substring(0, datosACalcular.length() - 1);
                    break;

                case "=":
                    if (datosACalcular.matches("([0-9]*\\.?)[0-9]+[-+*/]([0-9]*\\.?)[0-9]+")) {
                        String resultado = obtenerResultado(datosACalcular, operador);
                        datosACalcular = resultado;
                        tvResultado.setText(resultado);
                    } else {
                        Toast.makeText(this, "Operación no válida", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    if (txtBtn.matches("[0-9]+|\\.")) datosACalcular += txtBtn;
                    else if (txtBtn.matches("[-+*/]")) {
                        datosACalcular += txtBtn;
                        operador = txtBtn;
                    }
            }

            tvOperacion.setText(datosACalcular);

    }

    String obtenerResultado(String _datos, String _operador){

        // Separo el String según el _operador, por lo que recibiré 2 Strings
        String[] datos = _datos.split("[-+*/]");
        float num1 = Float.parseFloat(datos[0]);
        float num2 = Float.parseFloat(datos[1]);
        float resultado = 0;

        // Compruebo el operador recibido y realizo su operación correspondiente
        switch(_operador){
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                break;
            case "*":
                resultado = num1 * num2;
                break;
            case "/":
                resultado = num1 / num2;
                break;
        }

        // Compruebo si tiene decimal o termina en .0
        if (resultado != (int) resultado) {
            return String.valueOf(resultado);
        } else{
            return String.valueOf((int) resultado);
        }
    }
}