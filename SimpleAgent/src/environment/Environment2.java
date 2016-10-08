package environment;

import action.Action;
import result.Result;

/**
 * Second environnement
 * env2: e1 -> r2 , e2 -> r1   (i11 et i22 ne se produisent jamais)
 * @author Jérôme
 */
public class Environment2 implements Environment{
	@Override
	public Result giveResult(Action A) {
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
