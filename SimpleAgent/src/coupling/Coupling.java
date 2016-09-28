package coupling;

import action.Action;
import interaction.Interaction;
import result.Result;

/**
 * Interface devant être implémentée pour représenter
 * un système motivationnel
 * @author Jérôme
 */
public interface Coupling {
	/**
	 * La méthode doit éditer la valeur de l'interaction en fonction de son {@link Action} et de son {@link Result}
	 * @param interaction : {@link Interaction} à traiter
	 */
	public void motivate(Interaction interaction);
}
