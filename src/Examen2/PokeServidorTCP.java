/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YohovaniVargas
 */
public class PokeServidorTCP implements Runnable{
    private Socket client,client2;
	private Entrenador e1;
	private Entrenador e2;
	BufferedReader in = null;
	PrintWriter out = null;
	BufferedReader in2 = null;
	PrintWriter out2 = null;
	
    PokeServidorTCP(Socket client,Socket c2) {
		try {
			this.client = client;
			this.client2 = c2;
//			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
//			ObjectOutputStream oos2 = new ObjectOutputStream(client2.getOutputStream());
			System.out.println("Conectado con: "+client);
			System.out.println("Conectado con: "+client2);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
			in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
			out2 = new PrintWriter(client2.getOutputStream(), true);
			out.println("Nombre de Entrenador 1: ");
			out2.println("Nombre de Entrenador 2: ");
			Pokemon[] p1 = {new Pokemon(),new Pokemon(),new Pokemon(),new Pokemon()};
			Pokemon[] p2 = {new Pokemon(),new Pokemon(),new Pokemon(),new Pokemon()};
			e1 = new Entrenador(in.readLine(),p1);
			e2 = new Entrenador(in2.readLine(),p2);
//			oos.writeObject(e1);
//			oos2.writeObject(e2);
			System.out.println("Conectado con: " + e1.toString());
			System.out.println("Conectado con: " + e2.toString());
			out.println(e1.toString());
			out2.println(e2.toString());
		} catch (IOException ex) {
			Logger.getLogger(PokeServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
	

    public void run(){

        String line = "";

        while(line != null){
          try{
			  String r1="";
			  int res2,res1;
			if(e1.verificarEstatus()){
				System.out.println("-->Turno de: "+e1.getNombre());
				out.println("-->Selecciona Una Opción: "+"\n"+"1.-Atacar "+"\n"+"Cambiar de Pokemon 2: ");
				out.println("estatus del pokemon actual: "+e1.getPokemons()[e1.getPokActual()].toString());
				r1 = in.readLine();
				res1 = Integer.parseInt(r1);
				
				if(res1 == 1){
					int x = e1.getPokemons()[e1.getPokActual()].atacar();
					e2.getPokemons()[e2.getPokActual()].recibeAtaque(x);
					
					out.println("-->Le has ocacionado "+x+" de danno a: "+e2.getPokemons()[e2.getPokActual()]);
					out2.println("-->tu pokemon "+e2.getPokemons()[e2.getPokActual()].getName()+" ha recibido "+x+" de danno");
					if(!e2.getPokemons()[e2.getPokActual()].isEstatus()){
						out2.println("-->tu pokemon "+e2.getPokemons()[e2.getPokActual()].getName()+" ha sido derrotado");
						out.println("-->has derrotado al pokemon "+e2.getPokemons()[e2.getPokActual()].getName());
						if(e2.getPokActual() == 3){
							e2.setPokActual(0);
						}
						if(e2.verificarEstatus()){
							while(!e2.cambiarPokemon(e2.getPokActual()+1)){
								if(e2.getPokActual() == 3){
									e2.setPokActual(0);
								}
							}
						}else{
							out2.println("Tus pokemons ya no pueden continuar por lo tanto la medalla de este gimnasio es para "+e2.getNombre());
							out.println("Has obtenido la medalla de este gimnasio por derrotar a"+e1.getNombre());
							break;
						}
						out2.println("-->tu pokemon actual ahora es: "+e2.getPokemons()[e2.getPokActual()].getName());
					}
					
				}else{
					out.println(e1.mostrarPokemon());
					r1 = in.readLine();
					res2 = Integer.parseInt(r1);
					if(e1.cambiarPokemon(res2)){
						out.println("-->has cambiada a: "+e1.getPokemons()[e1.getPokActual()].toString());
						out2.println(e1.getNombre()+"-->ha cambiado de pokemon ahora usa a: "+e1.getPokemons()[e1.getPokActual()].toString());
					}else{
						r1 = in.readLine();
						res2 = Integer.parseInt(r1);
						while(!e1.cambiarPokemon(res2))
							out.println("Selecciona una opción valida");
						out.println("-->has cambiada a: "+e1.getPokemons()[e1.getPokActual()].toString());
						out2.println(e1.getNombre()+"-->ha cambiado de pokemon ahora usa a: "+e1.getPokemons()[e1.getPokActual()].toString());
					}
				}
			}else{
				out.println("Tus pokemons ya no pueden continuar por lo tanto la medalla de este gimnasio es para "+e2.getNombre());
				out2.println("Has obtenido la medalla de este gimnasio por derrotar a"+e1.getNombre());
				break;
			}
			if(e2.verificarEstatus()){
				System.out.println("Turno de: "+e2.getNombre());
				out2.println("Selecciona Una Opción: "+"\n"+"1.-Atacar "+"\n"+"2.-Cambiar de Pokemon: ");
				out2.println("estatus del pokemon actual: "+e2.getPokemons()[e2.getPokActual()].toString());
				r1 = in2.readLine();
				res1 = Integer.parseInt(r1);
				if(res1 == 1){
					int x = e2.getPokemons()[e2.getPokActual()].atacar();
					e1.getPokemons()[e1.getPokActual()].recibeAtaque(x);
					out2.println("Le has ocacionado "+x+" de danno a: "+e1.getPokemons()[e1.getPokActual()]);
					out.println("tu pokemon "+e1.getPokemons()[e1.getPokActual()].getName()+" ha recibido "+x+" de danno");
					if(!e1.getPokemons()[e1.getPokActual()].isEstatus()){
						out.println("tu pokemon "+e1.getPokemons()[e1.getPokActual()].getName()+" ha sido derrotado");
						out2.println("has derrotado al pokemon "+e1.getPokemons()[e1.getPokActual()].getName());
						if(e1.getPokActual() == 3){
							e1.setPokActual(0);
						}
						if(e1.verificarEstatus()){
							while(!e1.cambiarPokemon(e1.getPokActual()+1)){
								if(e1.getPokActual() == 3){
									e1.setPokActual(0);
								}
							}
						}else{
							out.println("Tus pokemons ya no pueden continuar por lo tanto la medalla de este gimnasio es para "+e1.getNombre());
							out2.println("Has obtenido la medalla de este gimnasio por derrotar a"+e2.getNombre());
							break;
						}
						
						out.println("tu pokemon actual ahora es"+e2.getPokemons()[e2.getPokActual()].getName());
					}
					
				}else{
					out2.println(e2.mostrarPokemon());
					r1 = in2.readLine();
					res2 = Integer.parseInt(r1);
					if(e2.cambiarPokemon(res2)){
						out2.println("has cambiada a: "+e2.getPokemons()[e2.getPokActual()].toString());
						out.println(e2.getNombre()+" ha cambiado de pokemon ahora usa a: "+e2.getPokemons()[e2.getPokActual()].toString());
					}else{
						r1 = in2.readLine();
						res2 = Integer.parseInt(r1);
						while(!e2.cambiarPokemon(res2))
						out2.println("Selecciona una opción valida");
						out2.println("has cambiada a: "+e1.getPokemons()[e1.getPokActual()].toString());
						out.println(e1.getNombre()+" ha cambiado de pokemon ahora usa a: "+e1.getPokemons()[e1.getPokActual()].toString());
					}
				}
			}else{
				out.println("Tus pokemons ya no pueden continuar por lo tanto la medalla de este gimnasio es para "+e1.getNombre());
				out2.println("Has obtenido la medalla de este gimnasio por derrotar a"+e2.getNombre());
				break;
			}
				
            
           } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
           }
        }
      
		try {
			client.close();
			System.out.println("conexion con: " + client + " terminada!");
		} catch (IOException ex) {
			Logger.getLogger(PokeServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
		}
        
    }
}
