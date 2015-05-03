package com.example.foodexpress.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListaAdapterProduto extends BaseAdapter {
    private Context context;
    private ArrayList<Produto> lista;

    public ListaAdapterProduto(Context r, ArrayList<Produto> lista){//construtor da classe, é runnable para poder ser exibido pela interface
        this.context = r;
        this.lista = lista;

    }

    //esses 4 métodos seguintes são padrão, e devem ser implementados de qualquer forma
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //esse método é executado para todos objetos da lista, ele é responsável por dar "forma a lista"

        Produto produto = lista.get(i);


        //"Inflar" o item, possibilita chamar os atributos da View como se fosse um objeto
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.activity_produto, null);

        assert layout != null;
        TextView nome = (TextView) layout.findViewById(R.id.t1);
        nome.setText(produto.getNome());
        ImageView iv = (ImageView) layout.findViewById(R.id.iv);
        iv.setImageBitmap(getImagem(produto.getImagem()));
        TextView preco = (TextView) layout.findViewById(R.id.textView);
        preco.setText("R$:  "+ produto.getValor());

        return layout;
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
