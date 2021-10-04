package com.automato;

import java.io.FileNotFoundException;

public class MainAutomato {

	public static void main(String[] args) throws FileNotFoundException {
		
		String path = "C:\\Users\\Eduardo\\3D Objects\\AutomatoFinitoLFA-main\\arquivoLeitura.txt";
		Automato automato = new Automato();
		
		automato.leituraEstados(path);
		automato.leituraInicial(path);
		automato.leituraFinal(path);
		automato.leituraAlfabeto(path);
		automato.leituraTransicoes(path);
		
		automato.pegaPalavra();
		
		automato.verificaPalavraDeterministo();
		
	}

}
