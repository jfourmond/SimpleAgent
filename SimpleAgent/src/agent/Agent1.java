package agent;

import java.util.List;

import action.Action;
import interaction.Interaction;
import interaction.InteractionComposite;
import result.Result;

public class Agent1 extends Agent {
	//	METHODES
	public Action chooseAction(Result result) {
		Action[] actions = Action.values();
		Action action = null;
		if(result == null) {
			// Teste de la première action
			action = actions[0];
			actionCount++;
		} else {
			if(actionCount < actions.length) {	// Teste de la / des autres actions
				action = actions[actionCount];
				actionCount++;
			} else {
				List<InteractionComposite> activatedComposites = activatedInteractionComposite(lastInteraction);
				if(activatedComposites.isEmpty())
					// Aucune interaction ne convient donc...
					action = randAction();
				else {
					for(InteractionComposite compo : activatedComposites) {
						Interaction post = compo.getPostInteraction();
						if(post.getValue() > 0) action = post.getAction();
					}
					
					if(action == null)
						// Aucune interaction ne convient donc...
						action = randAction();
				}
			}
		}
		return action;
	}
}
