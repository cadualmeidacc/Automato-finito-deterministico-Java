package com.automato;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Automato {
	
	//criando variaveis necessarias
	
	private ArrayList<Estado> estados = new ArrayList<Estado>();
	private ArrayList<String> alfabeto = new ArrayList<String>();
	private ArrayList<Transicao> transicoes = new ArrayList<Transicao>();
	
	private char[] palavraChar;
	private boolean isNaoDeterministico = false;
	private int posicao;
	
	
	// novo automato
	private ArrayList<Estado> novosEstados = new ArrayList<Estado>();
	private ArrayList<String> novosAlfabeto = new ArrayList<String>();
	private ArrayList<Transicao> novasTransicoes = new ArrayList<Transicao>();
	
	// conversao dos automatos para deterministico (criando novos automatos) / incompleto
	
	public ArrayList<Estado> novosEstados (){
		
		int contador = 0;
		int aux = 1;
		
		
		Estado estado = new Estado();
		estado.setNome("$");
		novosEstados.add(estado);
		
		for(int i = 0; i < estados.size(); i++) {
			novosEstados.get(i + 1).setNome(estados.get(i).getNome());
		}
		
		while(contador != estados.size() - 1) {
			if(estados.size() > contador + 1) {
				while(aux < estados.size()) {
					Estado estado1 = new Estado();
					estado1.setNome(estados.get(contador).getNome() + estados.get(aux).getNome());
					novosEstados.add(estado1);
					aux++;
				}
			}	
			contador++;
			aux = contador + 1;
		}
		
		Estado estado2 = new Estado();
		
		estado2.setNome(estados.get(0).getNome() + estados.get(1).getNome() + estados.get(2).getNome());
		novosEstados.add(estado2);
		estado2.setNome(estados.get(0).getNome() + estados.get(2).getNome() + estados.get(3).getNome());
		novosEstados.add(estado2);
		estado2.setNome(estados.get(1).getNome() + estados.get(2).getNome() + estados.get(3).getNome());
		novosEstados.add(estado2);
		
		
		return novosEstados;
	}
	
	public ArrayList<String> passaAlfabeto(){
		return null;
	}
	
	public ArrayList<Transicao> novasTransicoes(){
		
		return null;
	}
	
	//fim da conversao dos automatos
	
	
	
	// Parte com automato Deterministico
	
	public void verificaPalavraDeterministo() {
		String atual = null;
		boolean aceito = true;
		
		for(int i = 0; i < palavraChar.length; i++) {
			// verifica se o Automato é aceito
			if(aceito)
				aceito = false;
			else {
				System.out.println("Automato nao aceito!!");
				break;
			}
			aceito = false;
			// caso a entrada seja somente uma letra
			if(i == 0 && i == (palavraChar.length - 1)) {
				String nomeInicio = estadoInicial();
				for(int x = 0; x < transicoes.size(); x++) {
					if(transicoes.get(x).getLocal().equals(nomeInicio) && transicoes.get(x).getLetra().equals(palavraChar[i] + "")) {
						atual = transicoes.get(x).getDestino();
						for(int y = 0; y < estados.size(); y++) {
							if(estados.get(y).getNome().equals(atual) && estados.get(y).isFim() == true) {
								aceito = true;
								imprimeAceito();
								break;
							} else {
								aceito = false;
							}
						}
					}
				}
			}
			//entra quando o alfabeto tem mais de 1 letra
			if(i == 0 && aceito == false) {
				String nomeInicio = estadoInicial();
				for(int x = 0; x < transicoes.size(); x++) {
					if(transicoes.get(x).getLocal().equals(nomeInicio) && transicoes.get(x).getLetra().equals(palavraChar[i] + "")) {
						if(i == 0 && i == (palavraChar.length - 1)){
							aceito = false;
							break;
						}else {
							atual = transicoes.get(x).getDestino();
							aceito = true;
							break;
						}
						
					}
				}
				
				// entra quando esta na ultima letra do alfabeto
			}else if(i == (palavraChar.length - 1) && aceito == false) {
				aceito = true;
				for(int x = 0; x < transicoes.size(); x++) {
					if(aceito == false) {
						break;
					}
					if(transicoes.get(x).getLocal().equals(atual) && transicoes.get(x).getLetra().equals(palavraChar[i] + "")) {
						atual = transicoes.get(x).getDestino();
						
						for(int y = 0; y < estados.size(); y++) {
							if(estados.get(y).getNome().equals(atual) && estados.get(y).isFim() == true) {
								aceito = true;
								imprimeAceito();
								atual = null;
								break;
							} else {
								aceito = false;
							}
						}
					}
				}
				//pecorrendo a palavra 
			}else {
				for(int x = 0; x < transicoes.size(); x++) {
					if(transicoes.get(x).getLocal().equals(atual) && transicoes.get(x).getLetra().equals(palavraChar[i] + "")) {
						atual = transicoes.get(x).getDestino();
						aceito = true;
						break;
					}
				}
			}	
			
		}
		if(aceito == false) {
			System.out.println("Automato nao aceito!!");
			
		}
	}
		
	public void imprimeNaoAceito() {
		System.out.println("Automato nao aceito!!");
	}
		
	public void imprimeAceito() {
		System.out.println("Automato aceito!!");
	}
	// procura qual é o estado inicial
	public String estadoInicial() {
		for(int i = 0; i < estados.size(); i++) {
			if(estados.get(i).isInicio() == true) {
				return estados.get(i).getNome();
			}
		}
		
		return null;
	}
	// pega palavra do usário
	public void pegaPalavra () {
	
		System.out.println("Qual a palavra ? ");
		Scanner scan1 = new Scanner(System.in);
		String palavra = scan1.nextLine();
		palavraChar = palavra.toCharArray();
	}
		
	// Leitura do Arquivo
	
	public void  leituraEstados (String path) throws FileNotFoundException{
		
		Scanner in = new Scanner(new FileReader(path));
		while (in.hasNextLine()) {
		    String line = in.nextLine();
		    
		    if(line.equals("#states")){
		    	
		    }
		    
		    else if(line.equals("#initial")) {
		    	break;
		    }else {
		    	Estado estado = new Estado();
		    	estado.setNome(line);
		    	estados.add(estado);
		    } 
		}
		
	}
	// ver qual o estado inicial
	public void  leituraInicial (String path) throws FileNotFoundException {
		
		String inicial = "";
		Scanner in = new Scanner(new FileReader(path));
		
		while (in.hasNextLine()) {
		    String line = in.nextLine();
 		    if(line.equals("#initial")) {
		    	inicial = in.nextLine();
		    	for(int i = 0; i < estados.size(); i++) {
		    		if(estados.get(i).getNome().equals(inicial)) {
		    			estados.get(i).setInicio(true);
		    			break;
		    		}
		    	}
		    	break;
		    }  
		}
		
		
	}
	// ver quais são os estados finais
	public void  leituraFinal (String path) throws FileNotFoundException {

		Scanner in = new Scanner(new FileReader(path));
		
		while (in.hasNextLine()) {
		   String line = in.nextLine();
		   if(line.equals("#accepting")) {
			   line = in.nextLine();
			   while( !line.equals("#alphabet")) {
				   for(int i = 0; i < estados.size(); i++) {
					   if(estados.get(i).getNome().equals(line)) {
						   estados.get(i).setFim(true);
						   line = in.nextLine();
					   }
				   }  
			   }
			   break;
		   }
		}
		
	}
	// pega o alfabeto 
	public void  leituraAlfabeto (String path) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileReader(path));
		
		while (in.hasNextLine()) {
		   String line = in.nextLine();
		   if(line.equals("#alphabet")) {
			   line = in.nextLine();
			   while( !line.equals("#transitions")) {
				   alfabeto.add(line);  
				   line = in.nextLine();
			   }
			   break;
		   }
		}
		
	}
	
	
	// Proximos metodos ler as transições
	
	public void leituraTransicoes (String path) throws FileNotFoundException {
		leituraLocal(path);
		leituraLetra(path);
		leituraDestino(path);
	}
	
	public void leituraLocal (String path) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileReader(path));
		
		
		while(in.hasNextLine()) {
			String line = in.nextLine();
			if(line.equals("#transitions")) {
				while(in.hasNextLine()) {
					String local = "";
					line = in.nextLine();
					char[] lineChar = line.toCharArray();
					isNaoDeterministico = false;
					for(int i = 0; i < lineChar.length; i++) {
						if(lineChar[i] == ',') {
							isNaoDeterministico = true;
							break;
						}
					}
					
					for(int i = 0; i < lineChar.length; i++) {
						
						if(isNaoDeterministico == false) {
							if(lineChar[i] == ':') {
								Transicao transicao = new Transicao(null, null, local);
								transicoes.add(transicao);
								break;
							}else {
								local +=  lineChar[i];		
							}
						}else {
							if(lineChar[i] == ':') {
								Transicao transicao = new Transicao(null, null, local);
								Transicao transicao2 = new Transicao(null, null, local);
								transicoes.add(transicao);
								transicoes.add(transicao2);
								break;
							}else {
								local +=  lineChar[i];		
							}
						}
					}
				}
			}
		}
		
	}
	
	public void leituraLetra (String path) throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader(path));
		posicao = 0;
		while(in.hasNextLine()) {
			String line = in.nextLine();
			if(line.equals("#transitions")) {
				while(in.hasNextLine()) {
					line = in.nextLine();
					char[] lineChar = line.toCharArray();
					
					isNaoDeterministico = false;
					for(int i = 0; i < lineChar.length; i++) {
						if(lineChar[i] == ',') {
							isNaoDeterministico = true;
							break;
						}
					}
					
					
					for(int i = 0; i < lineChar.length; i++) {
						if(isNaoDeterministico == false) {
							if(lineChar[i] == ':') {
								transicoes.get(posicao).setLetra("" + lineChar[i+1]);
								posicao++;
								break;
							}
						}else {
							if(lineChar[i] == ':') {
								transicoes.get(posicao).setLetra("" + lineChar[i+1]);
								posicao++;
								transicoes.get(posicao).setLetra("" + lineChar[i+1]);
								posicao++;
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public void leituraDestino (String path) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileReader(path));
		posicao = 0;
		while(in.hasNextLine()) {
			String line = in.nextLine();
			if(line.equals("#transitions")) {
				while(in.hasNextLine()) {
					String local = "";
					line = in.nextLine();
					char[] lineChar = line.toCharArray();
					for(int i = 0; i < lineChar.length; i++) {
						if(lineChar[i] == '>') {
							int contador = 1;
							while( (i + contador) != lineChar.length && lineChar[i + (contador)] != ','){
								local = local + lineChar[i + contador];
								contador++;
							}
							transicoes.get(posicao).setDestino(local);
							posicao++;
							local = "";
						} else if(lineChar[i] == ',') {
							int contador = 1;
							while((i + contador) < lineChar.length){
								local = local + lineChar[i + contador];
								contador++;
							}
							transicoes.get(posicao).setDestino(local);
							posicao++;
						}
					}
				}
			}
		}
	}
	
	
}
