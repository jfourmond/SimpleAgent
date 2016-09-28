package environment;

import action.Action;
import result.Result;

/**
 * Interface devant être implémentée pour créer un environnement
 * @author Jérôme
 */
public interface Environment {
	public Result giveResult(Action A);
}
