package environment;

import action.Action;
import result.Result;

/**
 * Premier environnement
 * env1: a1 -> r1 , a2 -> r2   (i12 et i21 ne se produisent jamais)
 * @author Jérôme
 */
public class Environment1 implements Environment {
	@Override
	public Result giveResult(Action A) {
		switch(A) {
			case TRIANGLE:
				return Result.WHITE;
			case CIRCLE:
				return Result.GREEN;
			default:
				return null;
		}
	}
}
