package main;

import action.Action;
import agent.Agent;
import agent.Agent1;
import agent.Agent2;
import coupling.Coupling;
import coupling.Coupling1;
import coupling.Coupling2;
import coupling.Coupling3;
import environment.Environment;
import environment.Environment1;
import environment.Environment2;
import environment.Environment3;
import environment.Environment4;
import interaction.Interaction;
import interaction.InteractionComposite;
import result.Result;

public class MainEnv {
	private static Agent agent;
	private static Environment env;
	private static Coupling cp;
	private static int step;
	
	public static void main(String[] args) {
		if(args.length != 3 && args.length != 4) {
			System.err.println("Nombre d'arguments invalides (3 nécessaires, 1 optionnels) ");
			return;
		}
		
		int numAgent;
		int numEnv;
		int numCoup;
		int numStep;
		try {
			numAgent = Integer.parseInt(args[0]);
			numEnv = Integer.parseInt(args[1]);
			numCoup = Integer.parseInt(args[2]);
		} catch(Exception e) {
			System.err.println("Type d'argument invalide (entier nécessaire)");
			return;
		}
		try {
			numStep = Integer.parseInt(args[3]);
		} catch(Exception e) {
			numStep = 10;
		}
		
		try {
			build(numAgent, numEnv, numCoup, numStep);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}
		
		Result result = null;
		Action action = null;
		Interaction interaction = null;
		InteractionComposite compo = null;
		System.out.println("Agent " + numAgent + " , Environnement " + numEnv + ", Système Motivationnel " + numCoup + " Pas " + step + ":\n");
		for(int i=0 ; i<step ; i++) {
			action = agent.chooseAction(result);
			result = env.giveResult(action);
			
			interaction = new Interaction(action, result);
			cp.motivate(interaction);
			
			compo = agent.memorize(interaction);
			
			if(compo != null) System.out.println(interaction + "\t-\tMemorisation de " + compo);
			else System.out.println(interaction);
		}
	}
	
	private static void build(int numAgent, int numEnv, int numCoup, int numStep) throws Exception {
		//	Construction de l'Agent
		switch(numAgent) {
			case 1:
				agent = new Agent1();
				break;
			case 2:
				agent = new Agent2();
				break;
			default:
				throw new Exception("Erreur dans le numéro d'Agent : 1 ou 2");
		}
		//	Construction de l'Environnement
		switch(numEnv) {
			case 1:
				env = new Environment1();
				break;
			case 2:
				env = new Environment2();
				break;
			case 3:
				env = new Environment3();
				break;
			case 4:
				env = new Environment4();
				break;
			default:
				throw new Exception("Erreur dans le numéro d'Environnement : 1, 2, 3 ou 4");
		}
		// Construction du Système Motivationnel
		switch(numCoup) {
			case 1:
				cp = new Coupling1();
				break;
			case 2:
				cp = new Coupling2();
				break;
			case 3:
				cp = new Coupling3();
				break;
			default:
				throw new Exception("Erreur dans le numéro du système motivationnel : 1, 2 ou 3");
		}
		// Construction du pas
		step = numStep;
	}
}
