/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen2;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author YohovaniVargas
 */
public class Pokemon {
	final private String[] pokemons={"normal","fuego","agua","volador","eléctrico","planta","roca","hielo",
	"peleador","veneno","tierra","psíquico","insecto","fantasma","dragón", "siniestro","acero", "ada"};
	private String tipo;
	private String name;
	private int salud;
	private boolean estatus;
	private String[][] pokemones= {{"Meowth","Tauros","Eevee"},{"Charmander","Cyndaquill","Arcanine"},{"Vaporeon","Lapras","Squirtle"},
		{"Charizard","Pidgeotto","Zubat"},{"Pikachu","Jolteon","Ampharos"},{"Chikorita","Sceptile","Bulbasaur"},{"Golem","Onix","Kabutops"},
		{"Articuno","Abomasnow","Aurorus"},{"Machoke","Machamp","Lucario"},{"Arbok","Muk","Seviper"},{"Sandshrew","Cubone","Diglett"},
		{"Alakazam","Espeon","Mewtwo"},{"Beedrill","Scyther","Butterfree"},	{"Misdreavus","Haunter","Gengar"},{"Dragonite","Salamence","Latios"},
		{"Umbreon","Absol","Zoroark"},{"Steelix","Aggron","Metagross"},{"Togepi","Clefable","Sylveon"}};

	public Pokemon() {
		Random r = new Random();
		this.tipo = pokemons[r.nextInt(18)];
		int x=0;
		for(int i=0;i<pokemons.length;i++)
			if(tipo.equals(pokemons[i])){
				x=i;
				break;
			}
		this.name = pokemones[x][r.nextInt(3)];
		this.salud = 50;
		this.estatus = true;
	}
	
	public Pokemon(String tipo) {
		int x=0;
		for(int i=0;i<pokemons.length;i++)
			if(tipo.equals(pokemons[i])){
				x=i;
				break;
			}
		Random r = new Random();
		this.name = pokemones[x][r.nextInt(3)];
		this.tipo = tipo;
		this.salud = 50;
		this.estatus = true;
	}
	

	public void recibeAtaque(int x){
		salud-=x;
		if(salud <= 0){
			salud=0;
			estatus=false;
		}
	}
	
	@Override
	public String toString() {
		String aux="";
		if(estatus){
			aux="Pokemon: "+name+"\nTipo: "+tipo+"\nSalud: "+salud+"";
		}else{
			aux="Pokemon: "+name+"\nTipo: "+tipo+"\nSalud: "+salud+"\nha sido derrotado";
		}
		return aux;
	}
	
	public int atacar(){
		Random r = new Random();
		return r.nextInt(50);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
