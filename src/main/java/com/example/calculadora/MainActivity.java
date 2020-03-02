package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadora.lista_datos.ListaDatos;

public class MainActivity extends AppCompatActivity {

    private Button button;

    EditText campo1, campo2;
    TextView result;
    int num1,num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campo1 = (EditText) findViewById(R.id.campo1);
        campo2 = (EditText) findViewById(R.id.campo2);
        result = (TextView) findViewById(R.id.result);

        button = (Button) findViewById(R.id.listaDatos);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListaDatos();
            }
        });
    }

    public void openListaDatos() {
        Intent intent = new Intent(this, ListaDatos.class);
        startActivity(intent);
    }

    public void onClick(View view) {

        num1 = Integer.parseInt(campo1.getText().toString());
        num2 = Integer.parseInt(campo2.getText().toString());

        switch (view.getId()) {
            case R.id.btnSumar:
                sumar();
                break;
            case R.id.btnRestar:
                restar();
                break;
            case R.id.btnMult:
                mult();
                break;
            case R.id.btnDiv:
                div();
                break;
        }
    }

    public void sumar() {
        int suma = num1 + num2;
        result.setText("El resultado de la suma es: " + suma);
    }

    public void restar() {
        int resta = num1 - num2;
        result.setText("El resultado de la resta es: " + resta);
    }

    public void div() {

        if(num2 > 0) {
            int div = num1 / num2;
            result.setText("El resultado de la división es: " + div);
        } else {
            Toast.makeText(this, "El segundo numero no puede ser cero.", Toast.LENGTH_LONG).show();
        }
    }

    public void mult() {
        int mult = num1 * num2;
        result.setText("El resultado de la multiplicación es: " + mult);
    }
}
