package environment;

import action.Action;
import result.Result;


/**
 * Environnement 3
 * Retourne résultat r2 uniquement si l'agent alterne les actions
 * @author Jérôme
 */
public class Env3 implements Environment {
	private Action lastAction;
	
	//	CONSTRUCTEURS
	public Env3() { lastAction = null; }
	
	@Override
	public Result giveResult(Action A) {
		if(A == lastAction) {
			lastAction = A;
			return Result.WHITE;
		} else {
			lastAction = A;
			return Result.GREEN;
		}
	}

}
