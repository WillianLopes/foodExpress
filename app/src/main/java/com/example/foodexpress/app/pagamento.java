package com.example.foodexpress.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;


public class pagamento extends Activity {

    ArrayList<Produto> produtos = new ArrayList<Produto>();
    Pedido ped = new Pedido();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = getIntent();
                produtos = (ArrayList<Produto>) i.getSerializableExtra("produtos");
                ped = (Pedido) i.getSerializableExtra("pedido");

                RadioGroup rg = (RadioGroup) findViewById(R.id.radioSex);
                int op = rg.getCheckedRadioButtonId();

                if (op == R.id.dinheiro)
                    ped.setFormaPagamento(0);
                else if (op == R.id.credito)
                    ped.setFormaPagamento(1);
                else
                    ped.setFormaPagamento(2);

                EditText troco = (EditText) findViewById(R.id.editText);

                if (!troco.getText().toString().equals(""))
                    ped.setTroco(Double.parseDouble(troco.getText().toString()));

                Intent intent = new Intent(getApplicationContext(), carrinho.class);
                intent.putExtra("produtos", produtos);
                intent.putExtra("pedido", ped);
                startActivity(intent);
                finish();
            }
        });

    }


}
