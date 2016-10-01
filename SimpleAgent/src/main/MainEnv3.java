package main;

import action.Action;
import agent.Agent;
import coupling.Coupling;
import coupling.Coupling3;
import environment.Env3;
import environment.Environment;
import interaction.Interaction;
import interaction.InteractionComposite;
import result.Result;

/**
 * Traitement de l'agent avec l'environnement 3
 * @author Jérôme
 */
public class MainEnv3 {
	public static void main(String[] args) {
		Agent agent = new Agent();
		Environment env = new Env3();
		Coupling cp = new Coupling3();
		
		Result result = null;
		Action action = null;
		Interaction interaction = null;
		InteractionComposite compo = null;
		for(int i=0 ; i<10 ; i++) {
			action = agent.chooseAction(result);
			result = env.giveResult(action);
			
			interaction = new Interaction(action, result);
			cp.motivate(interaction);
			
			compo = agent.memorize(interaction);
			agent.setCycle(i);
			
			if(compo != null) System.out.println(interaction + "\t-\tMemorisation de " + compo);
		}
	}
}
