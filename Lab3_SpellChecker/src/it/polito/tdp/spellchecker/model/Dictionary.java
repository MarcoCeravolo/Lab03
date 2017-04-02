package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	ArrayList <String> dizionarioItaliano;
	ArrayList <String> dizionarioInglese;
	
	public Dictionary() {
		super();
		dizionarioItaliano = new ArrayList <String> ();
		dizionarioInglese = new ArrayList <String> ();
	}

	/**
	 * @return the dizionarioItaliano
	 */
	public ArrayList<String> getDizionarioItaliano() {
		return dizionarioItaliano;
	}

	/**
	 * @return the dizionarioInglese
	 */
	public ArrayList<String> getDizionarioInglese() {
		return dizionarioInglese;
	}
	
	public void loadDictionary(String language){
		if(language.compareTo("English")==0){
			try {
				FileReader fr = new FileReader("rsc/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					dizionarioInglese.add(word);
				}
				Collections.sort(dizionarioInglese);
				br.close();
			} catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}
		}
		else if(language.compareTo("Italian")==0){
			try {
				FileReader fr = new FileReader("rsc/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					dizionarioItaliano.add(word);
				}
				Collections.sort(dizionarioItaliano);
				br.close();
			} catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List <RichWord> listaParole = new ArrayList <RichWord>();
		
		for(String parola : inputTextList){
			if(dizionarioItaliano.contains(parola)){
				RichWord parolaCorretta = new RichWord(parola);
				parolaCorretta.isCorretta();
				listaParole.add(parolaCorretta);
				break;
			}
			else if (dizionarioInglese.contains(parola)){
				RichWord parolaCorretta = new RichWord(parola);
				parolaCorretta.isCorretta();
				listaParole.add(parolaCorretta);
				break;
			}
			RichWord parolaErrata = new RichWord(parola);
			listaParole.add(parolaErrata);
		}
		
		//RICERCA DICOTOMICA
		
		//L'IMPLEMENTAZIONE VALE SOLO PER IL DIZIONARIO INGLESE
		
		//PER QUELLO ITALIANO CAMBIARE "dizionarioInglese" in "dizionarioItaliano"
		//OPPURE NEL LOAD DIZIONARIO CARICARE ENTRAMBI I DIZIONARI CONTEMPORANEAMENTE
		
		/*List <RichWord> listaParole = new ArrayList <RichWord>();
		
		int first;
		int last;
		int middle;
		
		for(String parola : inputTextList){
			first = 0;
			last = dizionarioInglese.size() -1;
			
			while(first <= last){
				middle = (first+last)/2;
				if(dizionarioInglese.get(middle).equals(parola)){
					RichWord rw = new RichWord(parola);
					rw.isCorretta();
					listaParole.add(rw);
				}
				
				else if(dizionarioInglese.get(middle).compareTo(parola)>0){
					last=middle-1;
				}
				else
					first=middle+1;
			}
			if(!dizionarioInglese.contains(parola)){
				RichWord parolaErrata = new RichWord(parola);
				listaParole.add(parolaErrata);
			}
		}*/
		
		return listaParole;
	}
	
}
