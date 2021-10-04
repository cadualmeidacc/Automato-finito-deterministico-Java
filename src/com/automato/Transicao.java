package com.automato;

public class Transicao {
	String local;
	String letra;
	String destino;
	
	public Transicao(String letra, String destino, String local) {
		this.letra = letra;
		this.destino = destino;
		this.local = local;
	}
	
	//gets
	
	public String getDestino() {
		return destino;
	}
	public String getLocal() {
		return local;
	}
	public String getLetra() {
		return letra;
	}
	
	//sets
	
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public void setLocal(String local) {
		this.local = local;
	}
}
