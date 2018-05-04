/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen2;

/**
 *
 * @author YohovaniVargas
 */
public class Entrenador {
	private String nombre;
	private Pokemon[] pokemons;
	private boolean estatus;
	private int PokActual;

	public Entrenador(String serie) {
		decodificar(serie);
	}

	public Entrenador(String nombre, Pokemon[] pokemons) {
		this.nombre = nombre;
		this.pokemons = pokemons;
		estatus = true;
		this.PokActual = 0;
	}
	
	public String mostrarPokemon(){
		return "<----------------Lista de pokemons------------------->"+"Pokemon Actual: "+pokemons[this.PokActual].toString()+"\n"+"\n0.-"+pokemons[0].toString()
			+"\n1.-"+pokemons[1].toString()+"\n2.-"+pokemons[2].toString()+"\n3.-"+pokemons[3].toString()+"\n<----------------------------------->";
	}
	
	public boolean verificarEstatus(){
		int x=0;
		for(int i=0;i<4;i++)
			if(pokemons[i].isEstatus())
				x++;
		if(x > 0)
			return true;
		else
			return false;
	}

	public boolean cambiarPokemon(int x){
		if(x > 0 && x < 4){
			if(pokemons[x].isEstatus()){
				this.PokActual=x;
				System.out.println("Se ha cambiado al pokemon: "+this.pokemons[x].getTipo());
				return true;
			}else{
				System.out.println("No Se ha podido cambiar al pokemon");
				return false;
			}
		}else{
			System.out.println("No Se ha podido cambiar al pokemon");
			return false;
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pokemon[] getPokemons() {
		return pokemons;
	}

	public void setPokemons(Pokemon[] pokemons) {
		this.pokemons = pokemons;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public int getPokActual() {
		return PokActual;
	}

	public void setPokActual(int PokActual) {
		this.PokActual = PokActual;
	}
	
	public void mostarPok(){
		System.out.println(this.pokemons[0].getTipo()+" Salud: "+this.pokemons[0].getSalud()+" Estatus: "+this.pokemons[0].isEstatus());
	}

	private void decodificar(String serie) {
		String aux[] = serie.split(",");
		this.nombre = aux[0];
		pokemons = new Pokemon[4];
		pokemons[0] =new Pokemon(aux[1]);
		pokemons[1] =new Pokemon(aux[2]);
		pokemons[2] =new Pokemon(aux[3]);
		pokemons[3] =new Pokemon(aux[4]);
	}

	@Override
	public String toString() {
		return this.nombre+","+this.pokemons[0].getTipo()+","+this.pokemons[1].getTipo()+","+this.pokemons[2].getTipo()+","+this.pokemons[3].getTipo();
	}
	
	
}
