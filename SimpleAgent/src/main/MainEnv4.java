package main;

import action.Action;
import agent.Agent;
import agent.Agent2;
import coupling.Coupling;
import coupling.Coupling1;
import environment.Env4;
import environment.Environment;
import interaction.Interaction;
import interaction.InteractionComposite;
import result.Result;

/**
 * Traitement de l'agent avec l'environnement 4
 * @author Jérôme
 */
public class MainEnv4 {
	public static void main(String[] args) {
		Agent agent = new Agent2();
		Environment env = new Env4();
		Coupling cp = new Coupling1();
		
		Result result = null;
		Action action = null;
		Interaction interaction = null;
		InteractionComposite compo = null;
		
		System.out.println("Environnement 4 & Agent 2 :\n");
		for(int i=0 ; i<20 ; i++) {
			((Env4) env).setStep(i);
			action = agent.chooseAction(result);
			result = env.giveResult(action);
			
			interaction = new Interaction(action, result);
			cp.motivate(interaction);
			
			compo = agent.memorize(interaction);
			
			if(compo != null) System.out.println(interaction + "\t-\tMemorisation de " + compo);
			else System.out.println(interaction);
		}
	}
}
