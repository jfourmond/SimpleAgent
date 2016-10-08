package environment;

import action.Action;
import result.Result;

/**
 * Environnement 4
 * Se comporte comme {@link Environment1} pendant les 10 premiers cycles, puis comme {@link Environment2}
 * @author Jérôme
 */
public class Environment4 implements Environment {
	private int step;
	
	//	CONSTRUCTEURS
	public Environment4() {
		super();
		step = 0;
	}
	
	//	GETTERS
	public int getStep() { return step; }
	
	//	SETTERS
	public void setStep(int step) { this.step = step; }
	
	//	METHODES
	@Override
	public Result giveResult(Action A) {
		if(step < 10) {
			switch(A) {
				case TRIANGLE:
					return Result.GREEN;
				case CIRCLE:
					return Result.WHITE;
				default:
					return null;
			}
		} else {
			switch(A) {
				case CIRCLE:
					return Result.GREEN;
				case TRIANGLE:
					return Result.WHITE;
				default:
					return null;
			}
		}
	}
	
}
