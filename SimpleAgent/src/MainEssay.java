import action.Action;
import action.Actions;
import interaction.Interaction;
import main.Agent;
import result.Result;
import result.Results;



public class MainEssay {
	public static Action a1 = new Action(Actions.TRIANGLE);
	public static Action a2 = new Action(Actions.CIRCLE);

	public static Result r1 = new Result(Results.WHITE);
	public static Result r2 = new Result(Results.GREEN);

	public static Interaction i11a = new Interaction(a1, r1, 1);
	public static Interaction i11b = new Interaction(a1, r1, 1);
	
	public static Interaction i12 = new Interaction(a1, r2, 1);
	public static Interaction i21 = new Interaction(a2, r1, 1);
	public static Interaction i22 = new Interaction(a2, r2, 1);
	
	public static void main(String[] args) {
		Agent agent = new Agent();
		agent.memorize(i11a);
		// agent.memorize(i11b);
		System.out.println(agent.getMemories());
		System.out.println(agent.hasMemorized(i11b));
	}
}
