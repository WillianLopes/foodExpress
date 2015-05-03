package com.example.foodexpress.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class concluir extends Activity implements Runnable {

    ArrayList<Produto> produtos = new ArrayList<Produto>();
    Pedido ped = new Pedido();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concluir);

        Intent i = getIntent();
        produtos = (ArrayList<Produto>) i.getSerializableExtra("produtos");
        ped = (Pedido) i.getSerializableExtra("pedido");

        double valorTotal = 0;

        for (int x = 0; x < produtos.size(); x++) {
            String valores = produtos.get(x).getValor().replace("R$", "").replace(",", "");

            double um = Double.parseDouble(valores.substring(0, valores.length() - 2));
            double dois = Double.parseDouble("0." + valores.substring(valores.length() - 2, valores.length()));
            double result = um + dois;

            valorTotal += result;
        }

        ped.setValorTotal(valorTotal);

        Conexao c = new Conexao();
        Connection conn = c.conectar(getApplicationContext());

        if (conn != null) {

            try {
                CallableStatement cstmt = conn.prepareCall("{call dbo.sp_cadastrar_pedido(?, ?, ?, ?, ?, ?, ?)}");
                cstmt.setDouble(1, ped.getValorTotal());
                cstmt.setDouble(2, ped.getTroco());
                cstmt.setInt(3, ped.getFormaPagamento());
                cstmt.setInt(4, ped.getStatusPed());
                cstmt.setString(5, ped.getObservacoes());
                cstmt.setInt(6, ped.getId_cliente());
                cstmt.registerOutParameter(7, java.sql.Types.INTEGER);
                cstmt.execute();

                ped.setId(cstmt.getInt(7));

                for (int x = 0; x < produtos.size(); x++) {
                    cstmt = conn.prepareCall("{call dbo.sp_cadastrar_pedido_produto(?, ?)}");
                    cstmt.setInt(1, ped.getId());
                    cstmt.setDouble(2, produtos.get(x).getId());
                    cstmt.execute();
                }
                c.encerrar(conn);


            } catch (SQLException se) {
                Log.e("ERRO", se.getMessage());
            } catch (Exception e) {
                Log.e("ERRO", e.getMessage());
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(this, 3500);
    }

    public void run() {
        Intent it = new Intent();
        it.setClass(this, MainActivity.class);
        startActivity(it);
        finish();
    }
}