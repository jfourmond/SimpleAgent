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
	/**
	 * L'agent choisit l'action à effectuer en fonction de son expérience
	 * @param R : {@link Result}
	 * @return l'{@link Action} que l'agent a choisit d'après son expériene
	 */
	public Action chooseAction(Result R);
	
	/**
	 * L'agent mémorise l'{@link Interaction} passée en paramètre
	 * @param interaction : l'{@link Interaction} à mémoriser
	 * @return l'{@link InteractionComposite} construite
	 */
	public InteractionComposite memorize(Interaction interaction);
}
