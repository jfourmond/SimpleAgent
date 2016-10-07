package main;

import action.Action;
import agent.Agent;
import agent.Agent1;
import coupling.Coupling;
import coupling.Coupling1;
import environment.Env1;
import environment.Environment;
import interaction.Interaction;
import result.Result;

/**
 * Traitement de l'agent avec l'environnement 1
 * @author Jérôme
 */
public class MainEnv1 {
	public static void main(String[] args) {
		Agent agent = new Agent1();
		Environment env = new Env1();
		Coupling cp = new Coupling1();	// Coupling2()	// Coupling3()
		
		Result result = null;
		Action action = null;
		Interaction interaction = null;
		
		System.out.println("Environnement 1 & Agent 1 :\n");
		for(int i=0 ; i<10 ; i++) {
			action = agent.chooseAction(result);
			result = env.giveResult(action);
			
			interaction = new Interaction(action, result);
			cp.motivate(interaction);
			
			agent.memorize(interaction);
			System.out.println(interaction);
		}
	}
}
