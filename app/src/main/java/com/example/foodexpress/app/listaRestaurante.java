package com.example.foodexpress.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class listaRestaurante extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_restaurante);

        final ListView lv = (ListView) findViewById(R.id.listView); //esse é o componente lista

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b != null)
        {
            String str_categoria = (String) b.getString("str_categoria");

            Conexao c = new Conexao();
            Connection conn = c.conectar(getApplicationContext());

            if(conn != null) {
                try {
                    Statement statement = conn.createStatement();
                    ResultSet rs;

                    CallableStatement cstmt = conn.prepareCall("{call dbo.sp_buscar_restaurantes_categoria(?)}");
                    cstmt.setString(1, str_categoria);
                    cstmt.execute();

                    rs = cstmt.getResultSet();

                    ArrayList<Restaurante> lista = new ArrayList<Restaurante>();

                    if (!rs.next()) {
                        Toast.makeText(getApplicationContext(), "Nenhum restaurante foi encontrado.", Toast.LENGTH_SHORT).show();
                    }

                    do{
                        Restaurante r = new Restaurante();
                        r.setId(rs.getInt("id"));
                        r.setNome(rs.getString("nome"));
                        r.setImagem(rs.getString("imagem"));
                        r.setTelefone(rs.getString("telefone"));
                        r.setHorarioAbertura(rs.getString("abertura"));
                        r.setHorarioFechamento(rs.getString("fechamento"));
                        r.setEndereco(rs.getString("endereco"));
                        lista.add(r);

                    }while (rs.next());

                    c.encerrar(conn);

                    lv.setAdapter(new ListaAdapter(this, lista));
                    lv.setOnItemClickListener(implementaçaoClick(lista));


                } catch (SQLException se) {
                    Log.e("ERRO", se.getMessage());
                } catch (Exception e) {
                    Log.e("ERRO", e.getMessage());
                }
            }
        }

    }

    public AdapterView.OnItemClickListener implementaçaoClick(final ArrayList<Restaurante> lista){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {

                Restaurante rest = new Restaurante();
                rest = (Restaurante) av.getItemAtPosition(position);

                Intent passIntent = new Intent();
                passIntent.setClass(getBaseContext(), pageRestaurante.class);
                passIntent.putExtra("restaurante", rest);
                startActivity(passIntent);
                finish();

            }
        };

    }

}