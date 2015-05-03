package com.example.foodexpress.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class pageRestaurante extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_restaurante);
        ImageView iv = (ImageView) findViewById(R.id.imageView2);

        Intent i = getIntent();
        final Restaurante rest = (Restaurante) i.getSerializableExtra("restaurante");

        TextView nome = (TextView)findViewById(R.id.textView);
        nome.setText(rest.getNome());

        TextView horario = (TextView)findViewById(R.id.textView2);
        horario.setText("Horario de Funcionamento: " + rest.getHorarioAbertura().substring(0,5) + " / " + rest.getHorarioFechamento().substring(0,5));

        TextView telefone = (TextView)findViewById(R.id.textView3);
        telefone.setText("Telefone: " + rest.getTelefone());

        TextView endereco = (TextView)findViewById(R.id.textView6);
        endereco.setText(rest.getEndereco());

        ImageView iv2 = (ImageView)findViewById(R.id.imageView3);
        iv2.setImageBitmap(getImagem(rest.getImagem()));

        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(getApplicationContext(), selecionaPrato.class);
                activityChangeIntent.putExtra("id_restaurante", rest.getId());
                startActivity(activityChangeIntent);
                finish();
            }
        });

    }


    public static Bitmap getImagem(String src) {
        Bitmap myBitmap = null;
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

}