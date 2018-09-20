package com.example.a1143878729.cliente;

import android.util.Log;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    Socket s;
    Rceptor r;
    MainActivity activity;

    public Client(MainActivity activity){
        this.activity = activity;
    }
    @Override
    public void run() {

        try {
            s = new Socket("10.0.2.2", 5000);
            Log.e("DEBUG", "conexion exitosa");
            r = new Rceptor(s);
            r.setObserver(activity);
            r.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void enviar() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OutputStream outP = s.getOutputStream();
                    //escribe el mensaje
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(outP));
                    out.println("que paso ommme!");
                    out.flush();
                } catch (IOException ex){

                }
            }
        }).start();


    }
}