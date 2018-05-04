/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author YohovaniVargas
 */
public class ClienteRM implements Runnable{
    private Socket client;

    //Constructor
    ClienteRM(Socket client) {
        this.client = client;
        System.out.println("En escucha con: " + client);
    }

    public void run(){
        BufferedReader in = null;
        try{
          in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
          System.out.println(e.getMessage());
          System.exit(-1);
        }

        String msj = "";
        try {
            while ((msj = in.readLine()) != null) {
                System.out.println(msj);
                if (msj.contains("Bye.")) {
                    client.close();
                    System.exit(0);
                    break;
                }
            }
        } catch (IOException e) { 
            try {
                System.out.println("Conexion terminada con el Servidor");
                client.close();
                System.exit(-1);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                System.exit(-1);
            }
        }
    }
}
