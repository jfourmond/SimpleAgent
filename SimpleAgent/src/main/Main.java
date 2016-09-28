package main;
import action.Action;
import coupling.Coupling;
import coupling.Coupling1;
import environment.Env1;
import environment.Environment;
import interaction.Interaction;
import result.Result;

public class Main {
	/**
		public static Action a1 = new Action(Actions.TRIANGLE);
		public static Action a2 = new Action(Actions.CIRCLE);
		
		public static Result r1 = new Result(Results.WHITE);
		public static Result r2 = new Result(Results.GREEN);
		
		public static Interaction i11 = new Interaction(a1, r1, 1);
		public static Interaction i12 = new Interaction(a1, r2, 1);
		public static Interaction i21 = new Interaction(a2, r1, 1);
		public static Interaction i22 = new Interaction(a2, r2, 1);
	**/
	
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
			
			if(!agent.hasMemorized(interaction))
				agent.memorize(interaction);
			
			System.out.println(interaction);
		}
	}

}
