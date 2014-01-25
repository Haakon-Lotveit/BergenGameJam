package no.gamejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TalkEngine {
	private final File standardFile = new File("resources/text/dialog.snakk");
	private Random r;
	private HashMap<String, HashMap<String, ArrayList<String>>> dialog;
	public TalkEngine() throws FileNotFoundException {
		r = new Random();
		dialog = new HashMap<>();
		
		Scanner readReactions = new Scanner(this.standardFile);
		String reactionType, status, line;
		while(readReactions.hasNext()){
			reactionType = readReactions.next();
			status = readReactions.next();
			line = readReactions.nextLine();

			HashMap<String, ArrayList<String>> type;
			if(dialog.containsKey(reactionType)){
				type = dialog.get(reactionType);
			}
			else{
				type = new HashMap<>();
				dialog.put(reactionType, type);
			}
			ArrayList<String> lines;
			if(type.containsKey(status)){
				lines = type.get(status);
			}
			else{
				lines = new ArrayList<>();
				type.put(status, lines);
			}
			
			lines.add(line);
			
//			System.out.printf("Reaksjon av type %s, og status %s,%n%s%n",
//					reactionType, status, line);
		}
		readReactions.close();
		
	}
	public Pair<Integer,String> talk(Spiller spiller, Vakt vakt, TalkType type){
		Integer resultat;
		switch(type){
		case SMISK:
			resultat = spiller.getSmisk() - vakt.smisk();
			break;
		case SKREM:
			resultat = spiller.getSkrem() - vakt.skrem();
			break;
		case BLØFF:
			resultat = spiller.getBløff() - vakt.bløff();
			break;
		default:
			throw new RuntimeException("Invalid enum encountered: " + type);
		}
		
		String response = getResponse(type, resultat);
		
		return new Pair<Integer, String>(resultat, response);
	}
	
	public String getResponse(String type, String subType){
		if(dialog.containsKey(type)){
			if(dialog.get(type).containsKey(subType)){
				ArrayList<String> lst = dialog.get(type).get(subType);
				return lst.get(r.nextInt(lst.size()));
			}
		}
		return "[DEBUG]: Dialog ikke funnet. Finn Haakon og vis ham feilen!"; 
	}
	
	public List<String> getResponses(String type, String subType){
		if(dialog.containsKey(type)){
			if(dialog.get(type).containsKey(subType)){
				return dialog.get(type).get(subType);
			}
		}
		List<String> error = new LinkedList<>();
		error.add("[DEBUG]: Dialog ikke funnet. Finn Haakon og vis ham feilen!");
		return error;
	}
	
	/**
	 * Dette er en spesialisert metode for diplomati, med begrenset typesjekking.
	 * @param type
	 * @param result
	 * @return
	 */
	private String getResponse(TalkType type, Integer result){
		String resultat;
		if(result < 0){
			resultat = "MISLYKKET";
		} else if(result > 0){
			resultat = "VELLYKKET";
		} else{
			resultat = "NØYTRAL";
		}
		
		String retVal = String.format("%s resultat: %s%n", type.toString(), resultat);
		
		HashMap<String, ArrayList<String>> responseType;
		if(dialog.containsKey(type.toString())){
			responseType = dialog.get(type.toString());
		}
		else{
			return retVal;
		}		
		if(responseType.containsKey(resultat)){
			ArrayList<String> responses = responseType.get(resultat);
			return responses.get(r.nextInt(responses.size()));
		}
		else{
			return retVal;
		}
	}
}
