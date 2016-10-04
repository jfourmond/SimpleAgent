package agent;

import action.Action;
import interaction.Interaction;
import interaction.InteractionComposite;
import result.Result;

/**
 * Interface devant être implémentée pour représenter
 * un agent
 * @author Jérôme
 *
 */
public interface Agent {
	public Action chooseAction(Result R);
	
	public InteractionComposite memorize(Interaction interaction);
}
