package com.example.foodexpress.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class adicionaNota extends Activity {

    ArrayList<Produto> produtos = new ArrayList<Produto>();
    Pedido ped = new Pedido();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_nota);

        Intent i = getIntent();
        produtos = (ArrayList<Produto>) i.getSerializableExtra("produtos");

        Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText txt = (EditText) findViewById(R.id.editText);
                String text = txt.getText().toString();

                ped.setObservacoes(text);

                Intent activityChangeIntent = new Intent(getApplicationContext(), adicionarEndereco.class);
                activityChangeIntent.putExtra("produtos", produtos);
                activityChangeIntent.putExtra("pedido", ped);
                startActivity(activityChangeIntent);
                finish();

            }
        });
    }

}
