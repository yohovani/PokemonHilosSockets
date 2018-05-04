/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen2;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author YohovaniVargas
 */
public class ServidorTCP {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(20011);
            System.out.println("Servidor en ejecución...\n");

            while(true){
                PokeServidorTCP w;
                try {
                    w = new PokeServidorTCP(server.accept(),server.accept());
                    Thread t = new Thread(w);
                    t.start();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.exit(-1);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        
    }
}
