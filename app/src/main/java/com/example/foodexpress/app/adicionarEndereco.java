package com.example.foodexpress.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class adicionarEndereco extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_endereco);

        Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<Produto> produtos = new ArrayList<Produto>();
                Pedido ped = new Pedido();

                Intent i = getIntent();
                produtos = (ArrayList<Produto>) i.getSerializableExtra("produtos");
                ped = (Pedido) i.getSerializableExtra("pedido");

                EditText nome = (EditText) findViewById(R.id.editText);
                EditText telefone = (EditText) findViewById(R.id.editText2);
                EditText bairro = (EditText) findViewById(R.id.editText3);
                EditText endereco = (EditText) findViewById(R.id.editText4);

                if(nome.getText().toString().equals("") || endereco.getText().toString().equals("") ||
                   bairro.getText().toString().equals("") || nome.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Existem campos vazios. Preencha-os", Toast.LENGTH_SHORT).show();

                } else {

                    Conexao c = new Conexao();
                    Connection conn = c.conectar(getApplicationContext());

                    if (conn != null) {

                        try {

                            CallableStatement cstmt = conn.prepareCall("{call dbo.sp_cadastrar_cliente(?, ?, ?, ?, ?)}");
                            cstmt.setString(1, nome.getText().toString());
                            cstmt.setString(2, endereco.getText().toString() + " / Bairro: " + bairro.getText().toString());
                            cstmt.setString(3, telefone.getText().toString());
                            cstmt.setInt(4, 0);
                            cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
                            cstmt.execute();

                            ped.setId_cliente(cstmt.getInt(5));

                            c.encerrar(conn);

                        } catch (SQLException se) {
                            Log.e("ERRO", se.getMessage());
                        } catch (Exception e) {
                            Log.e("ERRO", e.getMessage());
                        }

                        Intent activityChangeIntent = new Intent(getApplicationContext(), pagamento.class);
                        activityChangeIntent.putExtra("produtos", produtos);
                        activityChangeIntent.putExtra("pedido", ped);
                        startActivity(activityChangeIntent);
                        finish();
                    }
                }
            }
        });

    }


}
