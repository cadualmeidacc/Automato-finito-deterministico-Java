package com.automato;

public class Estado {
	private String nome;
	private boolean inicio;
	private boolean fim;
	
	
	
	public String getNome() {
		return nome;
	}
	public boolean isFim() {
		return fim;
	}
	public boolean isInicio() {
		return inicio;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setFim(boolean fim) {
		this.fim = fim;
	}
	public void setInicio(boolean inicio) {
		this.inicio = inicio;
	}
}
