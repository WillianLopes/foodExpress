package com.example.foodexpress.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.apache.commons.net.ftp.*;

public class ListaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Restaurante> lista;
    FTPClient mFTPClient = null;
    Bitmap tempBit = null;


    public ListaAdapter(Context r, ArrayList<Restaurante> lista){//construtor da classe, é runnable para poder ser exibido pela interface
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

        Restaurante restaurante = lista.get(i);

        //"Inflar" o item, possibilita chamar os atributos da View como se fosse um objeto
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.activity_restaurante, null);

        assert layout != null;
        TextView nome = (TextView) layout.findViewById(R.id.t1);
        nome.setText(restaurante.getNome());

        ImageView iv = (ImageView) layout.findViewById(R.id.iv);
        iv.setImageBitmap(getImagem(restaurante.getImagem()));
        //ftpDownload();
        //iv.setImageBitmap(tempBit);

        return layout;
    }

    public void ftpDownload() {

        try {

            Bitmap myBitmap = null;
            mFTPClient = new FTPClient();
            // connecting to the host
            //mFTPClient.connect("192.168.1.8", 2100);
            mFTPClient.connect("179.104.163.181", 2100);

            // Now check the reply code, if positive mean connection success
            if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {

                // Login using username & password
                boolean status = mFTPClient.login("user", "user");
                mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
                mFTPClient.enterLocalPassiveMode();


                //Recupera o caminho padrão do SDCARD
                File root = Environment.getRootDirectory();

                //Cria arquivo para gravar o texto
                File file= new File(root, "sobrinholindo.jpg");

                mFTPClient.changeWorkingDirectory("/server");
                ftpDownload("sobrinholindo.jpg", file.getAbsolutePath()+"/"+file.getName());

                mFTPClient.disconnect();


                tempBit = BitmapFactory.decodeFile(file.getAbsolutePath());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

    public boolean ftpDownload(String srcFilePath, String desFilePath)
    {
        boolean status = false;
        try {
            //Cria o outputStream para ser passado como parametro
            FileOutputStream desFileStream = new FileOutputStream(desFilePath);;

            //Tipo de arquivo
            mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);

            //http://commons.apache.org/net/apidocs/org/apache/commons/net/ftp/FTPClient.html#enterLocalActiveMode()
            mFTPClient.enterLocalPassiveMode();

            //Faz o download do arquivo
            status = mFTPClient.retrieveFile(srcFilePath, desFileStream);

            //Fecho o output
            desFileStream.close();


            return status;
        } catch (Exception e) {
            Log.e("Log", "download falhou");

        }

        return status;
    }

}
