package com.example.foodexpress.app;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {

    public Connection conectar(Context c)
    {
        String _user = "sa";
        String _pass = "123456";
        String _DB = "foodexpress";
       // String _server = "192.168.1.8:1433";
        String _server = "179.104.163.181:1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;

        try {

            int s = getConnectivityStatusString(c);

            if (s == 3){
                Toast.makeText(c,"Não foi possível conectar a internet. Por favor, tente mais tarde",Toast.LENGTH_SHORT).show();

                return null;
            }
            else {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                ConnURL = "jdbc:jtds:sqlserver://" + _server + ";" + "databaseName=" + _DB + ";user=" + _user + ";password=" + _pass + ";";
                conn = DriverManager.getConnection(ConnURL);

                return conn;
            }

        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO",e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO",e.getMessage());
        }

        return conn;
    }

    public void encerrar(Connection conn)
    {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        int status = 0;
        if (conn == NetworkUtil.TYPE_WIFI) {
            status = 1;
        } else if (conn == NetworkUtil.TYPE_MOBILE) {
            status = 2;
        } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
            status = 3;
        }
        return status;
    }
}
