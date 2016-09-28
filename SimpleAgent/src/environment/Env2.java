package environment;

import action.Action;
import result.Result;
import result.Results;

/**
 * Second environnement
 * env2: e1 -> r2 , e2 -> r1   (i11 et i22 ne se produisent jamais)
 * @author Jérôme
 */
public class Env2 implements Environment{
	@Override
	public Result giveResult(Action A) {
		switch(A.getAction()) {
			case CIRCLE:
				return new Result(Results.WHITE);
			case TRIANGLE:
				return new Result(Results.GREEN);
			default:
				return null;
		}
	}

}
