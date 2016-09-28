package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import action.Action;
import action.Actions;
import interaction.Interaction;
import result.Result;

public class Agent {
	//	"Mémoire" de l'agent
	private List<Interaction> memories;
	
	//	CONSTRUCTEURS
	public Agent() { memories = new ArrayList<>(); }
	
	//	GETTERS
	public List<Interaction> getMemories() { return memories; }
	
	//	SETTERS
	public void setMemories(List<Interaction> memories) { this.memories = memories; }
	
	//	METHODES
	public Action chooseAction(Result result) {
		Actions[] actions = Actions.values();
		Actions action = null;
		if(result.getResult() == null) {
			// Teste de la première action
			action = actions[0];
		} else {
			// Tester le reste des actions
			Set<Actions> actionsMemorized = actionMemorized();
			if(actionsMemorized.size() < actions.length) {
				for(Actions a : actions) {
					if(!actionsMemorized.contains(a)) action = a;
				}
			} else {
				// Récupération de l'interaction la plus positive
				Interaction bestInteraction = bestInteraction(memories);
				if(bestInteraction != null)
					return bestInteraction.getAction();
			}
		}
		return new Action(action);
	}
	
	/**
	 * Mémorisation de l'interaction
	 * @param interaction : {@link Interaction} à ajouter à la mémoire
	 */
	public void memorize(Interaction interaction) {
		memories.add(interaction);
	}
	
	/**
	 * Teste si l'interaction est mémorisée
	 * @param interaction : {@link Interaction} à tester
	 * @return <code>true</code> si l'interaction est mémorisée, <code>false</code> sinon
	 */
	public boolean hasMemorized(Interaction interaction) {
		for(Interaction I : memories) {
			if(interaction.equals(I))
				return true;
		}
		return false;
	}
	
	/**
	 * Retourne les {@link Action} mémorisées
	 * @return les {@link Action} mémorisées
	 */
	public Set<Actions> actionMemorized() {
		Set<Actions> actions = new HashSet<>();
		for(Interaction interaction : memories) {
			actions.add(interaction.getAction().getAction());
		}
		return actions;
	}
	
	//	METHODES STATIQUES
	/**
	 * Retourne la meilleure interaction de la liste d'interactions
	 * @param interactions : {@link Set} d'{@link Interaction} à parcourir
	 * @return la meilleure {@link Interaction}
	 */
	private static Interaction bestInteraction(List<Interaction> interactions) {
		if(!interactions.isEmpty()) {
			Interaction bestInteraction = interactions.get(0);
			for(Interaction interaction : interactions) {
				if(interaction.getValue() > bestInteraction.getValue())
					bestInteraction = interaction;
			}
			return bestInteraction;
		} else return null;
	}
}
