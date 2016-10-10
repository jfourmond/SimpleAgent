package agent;

import java.util.ArrayList;
import java.util.List;

import action.Action;
import interaction.Interaction;
import interaction.InteractionComposite;
import result.Result;

public class Agent2 extends Agent {
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
				if(activatedComposites.isEmpty()) {
					// Aucune interaction ne convient donc...
					action = randAction();
				} else {
					List<Proposition> propositionsInteraction;
					List<Proposition> propositionsAction;
					propositionsInteraction = buildPropositionsInteraction(activatedComposites);
					propositionsAction = buildPropositionsAction(propositionsInteraction);
					action = getMaxAction(propositionsAction);
				}
			}
		}
		return action;
	}
	
	/**
	 * Construit une {@link List} de {@link Proposition} à partir d'une {@link List} d'{@link InteractionComposite}.
	 * @param composites : la {@link List} d'{@link InteractionComposite}
	 * @return une {@link List} de {@link Proposition}
	 */
	private List<Proposition> buildPropositionsInteraction(List<InteractionComposite> composites) {
		List<Proposition> propositions = new ArrayList<>();
		for(InteractionComposite compo : composites) {
			for(Interaction interaction : compo.interactions())
				propositions.add(new Proposition(interaction.getAction(), compo.getWeight() * interaction.getValue()));
		}
		return propositions;
	}
	
	/**
	 * Construit une {@link List} de {@link Proposition} à partir d'une {@link List} de {@link Proposition} basée sur des {@link Interaction}s
	 * @param propositionsInteraction : {@link List} de {@link Proposition} basée sur des {@link Interaction}s
	 * @return une {@link List} de {@link Proposition}
	 */
	private List<Proposition> buildPropositionsAction(List<Proposition> propositionsInteraction) {
		List<Proposition> propositions = new ArrayList<>();
		Action[] actions = Action.values();
		for(Action action : actions) {
			Proposition proposition = new Proposition(action, 0);
			int n = getFullProclivityAction(propositionsInteraction, action);
			proposition.setProclivity(n);
			propositions.add(proposition);
		}
		return propositions;
	}
	
	/**
	 * Retourne la valeur maximal de la propension de l'action passée en paramètre à partir de la {@link List} de {@link Proposition} passée en paramètre
	 * @param propositions : {@link List} de {@link Proposition} à parcourir
	 * @param action : {@link Action} dont la méthode calcule la propension
	 * @return la propension de l'action
	 */
	private int getFullProclivityAction(List<Proposition> propositions, Action action) {
		int n = 0;
		for(Proposition proposition : propositions) {
			if(proposition.getAction() == action)
				n += proposition.getProclivity();
		}
		return n;
	}

	/**
	 * Retourne l'{@link Action} dont la propension est la plus élevée dans la {@link List} de {@link Proposition} passée en paramètre
	 * @param propositionsAction : {@link List} de {@link Proposition}
	 * @return l'{@link Action} dont la propension est la plus élevée
	 */
	private Action getMaxAction(List<Proposition> propositionsAction) {
		Action action = null;
		if(!propositionsAction.isEmpty()) {
			Proposition bp = propositionsAction.get(0);
			for(Proposition proposition : propositionsAction) {
				if(proposition.getProclivity() > bp.getProclivity())
					bp = proposition;
			}
			action = bp.getAction();
		}
		return action;
	}
}
