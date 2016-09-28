package main;
import action.Action;
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
		Agent agent = new Agent();
		Environment env = new Env1();	// Env2();		// Env3();
		Coupling cp = new Coupling1();	// Coupling2;	// Coupling 3;
		
		Result result = null;
		Action action = null;
		Interaction interaction;
		
		for(int i=0 ; i<10 ; i++) {
			action = agent.chooseAction(result);
			result = env.giveResult(action);
			
			interaction = new Interaction(action, result);
			
			cp.motivate(interaction);
			
			agent.memorize(interaction);
			
			System.out.println(interaction);
			agent.setCycle(i);
		}
	}
}