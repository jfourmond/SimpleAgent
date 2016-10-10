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
		Result R;
		if(step < 10) {
			switch(A) {
				case TRIANGLE:
					R = Result.WHITE;
					break;
				case CIRCLE:
					R = Result.GREEN;
					break;
				default:
					return null;
			}
		} else {
			switch(A) {
				case TRIANGLE:
					R = Result.GREEN;
					break;
				case CIRCLE:
					R = Result.WHITE;
					break;
				default:
					return null;
			}
		}
		step++;
		return R;
	}
	
}
