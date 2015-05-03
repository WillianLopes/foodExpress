package com.example.foodexpress.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


public class filtrosRestaurante extends Activity {

    String chk_itens = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros_restaurante);

        Button b1 = (Button) findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent activityChangeIntent = new Intent(getApplicationContext(), listaRestaurante.class);
                activityChangeIntent.putExtra("str_categoria", chk_itens);
                startActivity(activityChangeIntent);
                finish();

            }
        });
    }

    public void onCheckboxClicked(View v) {
        CheckBox chk1 = (CheckBox) findViewById(R.id.checkBox);
        CheckBox chk2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox chk3 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox chk4 = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox chk5 = (CheckBox) findViewById(R.id.checkBox5);
        CheckBox chk6 = (CheckBox) findViewById(R.id.checkBox6);
        CheckBox chk7 = (CheckBox) findViewById(R.id.checkBox7);
        CheckBox chk8 = (CheckBox) findViewById(R.id.checkBox8);

        switch (v.getId()) {

            case R.id.checkBox:
                ImageView img = (ImageView) findViewById(R.id.imageView);

                if (chk1.isChecked()) {
                    img.setImageResource(R.drawable.gourmet50);
                    chk_itens += "Gourmet;";
                } else {
                    img.setImageDrawable(null);
                    chk_itens = chk_itens.replace("Gourmet;", "");
                }

                break;

            case R.id.checkBox2:
                ImageView img2 = (ImageView) findViewById(R.id.imageView2);

                if (chk2.isChecked()) {
                    img2.setImageResource(R.drawable.pizza);
                    chk_itens += "Pizza;";
                } else {
                    img2.setImageDrawable(null);
                    chk_itens = chk_itens.replace("Pizza;", "");
                }

                break;

            case R.id.checkBox3:
                ImageView img3 = (ImageView) findViewById(R.id.imageView3);

                if (chk3.isChecked()) {
                    img3.setImageResource(R.drawable.japanese50);
                    chk_itens += "Japonesa;";
                } else {
                    img3.setImageDrawable(null);
                    chk_itens = chk_itens.replace("Japonesa;", "");
                }

                break;

            case R.id.checkBox4:
                ImageView img4 = (ImageView) findViewById(R.id.imageView4);

                if (chk4.isChecked()) {
                    img4.setImageResource(R.drawable.pasta50);
                    chk_itens += "Massas;";
                } else {
                    img4.setImageDrawable(null);
                    chk_itens = chk_itens.replace("Massas;", "");
                }

                break;

            case R.id.checkBox5:
                ImageView img5 = (ImageView) findViewById(R.id.imageView5);

                if (chk5.isChecked()) {
                    img5.setImageResource(R.drawable.chinese50);
                    chk_itens += "Chinesa;";
                } else {
                    img5.setImageDrawable(null);
                    chk_itens = chk_itens.replace("Chinesa;", "");
                }

                break;

            case R.id.checkBox6:
                ImageView img6 = (ImageView) findViewById(R.id.imageView6);

                if (chk6.isChecked()) {
                    img6.setImageResource(R.drawable.burgah50);
                    chk_itens += "FastFood;";
                } else {
                    img6.setImageDrawable(null);
                    chk_itens = chk_itens.replace("FastFood;", "");
                }

                break;

            case R.id.checkBox7:
                ImageView img7 = (ImageView) findViewById(R.id.imageView7);

                if (chk7.isChecked()) {
                    img7.setImageResource(R.drawable.mexican50);
                    chk_itens += "Mexicana;";
                } else {
                    img7.setImageDrawable(null);
                    chk_itens = chk_itens.replace("Mexicana;", "");
                }

                break;

            case R.id.checkBox8:
                ImageView img8 = (ImageView) findViewById(R.id.imageView8);

                if (chk8.isChecked()) {
                    img8.setImageResource(R.drawable.dessert50);
                    chk_itens += "Sobremesa;";
                } else {
                    img8.setImageDrawable(null);
                    chk_itens = chk_itens.replace("Sobremesa;", "");
                }

                break;

        }
    }
}
