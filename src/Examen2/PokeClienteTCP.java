/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YohovaniVargas
 */
public class PokeClienteTCP {
    public static void main(String[] args) {
	
		
	String hostName = "127.0.0.1";
	int portNumber = 20011;
	try {
            InetAddress address = InetAddress.getByName(hostName);
            Socket clientSocket = new Socket(address, portNumber);
			
			OutputStream os = clientSocket.getOutputStream();
			DataOutputStream salida = new DataOutputStream(os);
			InputStream is = clientSocket.getInputStream();
			DataInputStream entrada = new DataInputStream(is);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//			ObjectInputStream perEnt = new ObjectInputStream(clientSocket.getInputStream());

			
            ClienteRM l;
            try {
                l = new ClienteRM(clientSocket);
                Thread t = new Thread(l);
                t.start();
            } catch (Exception e) { System.out.println(e.getMessage()); }
	
            PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true);
			
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			out.println(stdIn.readLine());
            String userInput;
			
            while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput); 
                System.out.print(">"); 
            }
            clientSocket.close();
            System.out.println("se termino el juego");
	}
        catch (IOException e) { e.printStackTrace(); }
    }
}
