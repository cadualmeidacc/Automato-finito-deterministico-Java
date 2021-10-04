package com.automato;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Automato {
	
	private ArrayList<Estado> estados = new ArrayList<Estado>();
	private ArrayList<String> alfabeto = new ArrayList<String>();
	
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
}
