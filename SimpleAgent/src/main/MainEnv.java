package main;

import action.Action;
import agent.Agent;
import agent.Agent1;
import agent.Agent2;
import coupling.Coupling;
import coupling.Coupling1;
import coupling.Coupling2;
import coupling.Coupling3;
import environment.Environment1;
import environment.Environment2;
import environment.Environment3;
import environment.Environment4;
import environment.Environment;
import interaction.Interaction;
import result.Result;

public class MainEnv {
	public static void main(String[] args) {
		//	AGENT	ENVIRONNEMENT	COUPLING
		if(args.length != 3) {
			System.err.println("Nombre d'arguments invalides (3 nécessaires) ");
			return;
		}
		Agent agent = null;
		Environment env = null;
		Coupling cp = null;
		
		int numAgent;
		int numEnv;
		int numCoup;
		try {
			numAgent = Integer.parseInt(args[0]);
			numEnv = Integer.parseInt(args[1]);
			numCoup = Integer.parseInt(args[2]);
		} catch(Exception e) {
			System.err.println("Type d'argument invalide (entier nécessaire)");
			return;
		}
		
		try {
			build(numAgent, numEnv, numCoup, agent, env, cp);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}
			
		agent = new Agent1();
		env = new Environment1();
		cp = new Coupling1();	// Coupling2()	// Coupling3()
		
		Result result = null;
		Action action = null;
		Interaction interaction = null;
		
		System.out.println("Agent " + numAgent + " , Environnement " + numEnv + ", Système Motivationnel : " + numCoup + " :\n");
		for(int i=0 ; i<10 ; i++) {
			action = agent.chooseAction(result);
			result = env.giveResult(action);
			
			interaction = new Interaction(action, result);
			cp.motivate(interaction);
			
			agent.memorize(interaction);
			System.out.println(interaction);
		}
	}
	
	private static void build(int numAgent, int numEnv, int numCoup, Agent agent, Environment env, Coupling cp) throws Exception {
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
	}
}
