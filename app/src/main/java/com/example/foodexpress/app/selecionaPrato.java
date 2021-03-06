package com.example.foodexpress.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class selecionaPrato extends Activity {

    ArrayList<Produto> produtos = new ArrayList<Produto>();
    int id_restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurante);

        final ListView lv = (ListView) findViewById(R.id.listView); //esse é o componente lista
        Button button = (Button) findViewById(R.id.button);

        Intent i = getIntent();

        id_restaurante = i.getIntExtra("id_restaurante", 0);

        Conexao c = new Conexao();
        Connection conn = c.conectar(getApplicationContext());

        if (conn != null) {

            try {

                Statement statement = conn.createStatement();
                ResultSet rs;

                CallableStatement cstmt = conn.prepareCall("{call dbo.sp_buscar_produtos_restaurante(?,?)}");
                cstmt.setInt(1, id_restaurante);
                cstmt.setInt(2, 0);
                cstmt.execute();

                rs = cstmt.getResultSet();

                ArrayList<Produto> lista = new ArrayList<Produto>();

                if (!rs.next()) {
                    Toast.makeText(getApplicationContext(), "Nenhum prato foi encontrado.", Toast.LENGTH_SHORT).show();
                }

                do {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setImagem(rs.getString("imagem"));
                    p.setId_restaurante(rs.getInt("id_restaurante"));
                    p.setValor(rs.getString("valor"));
                    lista.add(p);
                } while (rs.next());

                c.encerrar(conn);

                lv.setAdapter(new ListaAdapterProduto(this, lista));
                lv.setOnItemClickListener(implementaçaoClick(lista));

            } catch (SQLException se) {
                Log.e("ERRO", se.getMessage());
            } catch (Exception e) {
                Log.e("ERRO", e.getMessage());
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), selecionaBebida.class);
                    intent.putExtra("produtos", produtos);
                    intent.putExtra("id_restaurante", id_restaurante);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    public AdapterView.OnItemClickListener implementaçaoClick(final ArrayList<Produto> lista) {
        return (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {

                produtos.add((Produto) av.getItemAtPosition(position));

                Intent passIntent = new Intent();
                passIntent.setClass(getBaseContext(), selecionaBebida.class);
                passIntent.putExtra("produtos", produtos);
                passIntent.putExtra("id_restaurante", id_restaurante);
                startActivity(passIntent);
                finish();


            }
        });

    }

}
