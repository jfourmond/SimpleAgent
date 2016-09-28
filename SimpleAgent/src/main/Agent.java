package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import action.Action;
import interaction.Interaction;
import interaction.InteractionComposite;
import result.Result;

public class Agent {
	//	"Mémoire" de l'agent
	private List<InteractionComposite> memories;
	
	private int cycle;
	
	//	CONSTRUCTEURS
	public Agent() {
		memories = new ArrayList<>();
		cycle = 0;
	}
	
	//	GETTERS
	public List<InteractionComposite> getMemories() { return memories; }
	
	public int getCycle() { return cycle; }
	
	//	SETTERS
	public void setMemories(List<InteractionComposite> memories) { this.memories = memories; }
	
	public void setCycle(int cycle) { this.cycle = cycle; }
	
	//	METHODES
	public Action chooseAction(Result result) {
		// TODO Revoir code, revoir raisonnement (deuxième if)
		Action[] actions = Action.values();
		Action action = null;
		if(result == null) {
			// Teste de la première action
			action = actions[0];
		} else {
			// Tester le reste des actions
			Set<Interaction> interactionsMemorized = interactionMemorized();
			if(interactionsMemorized.size() < actions.length) {
				Set<Action> actionsMemorized = actionsMemorized(interactionsMemorized);
				for(Action a : actions) {
					if(!actionsMemorized.contains(a)) action = a;
				}
			} else {
				InteractionComposite IC = lastInteractionComposite();
				List<Action> actionsNotUsed = actionsNotUsedLast();
				if(actionsNotUsed.isEmpty()) {
					action = getLastPositiveAction();
				} else {
					System.out.println("YOLO");
				}
				// TODO Trouver interaction qui fera peut-être augmenter la valence
				// en cherchant une interaction complexe semblable sinon
				// tenter autre chose
				
			}
		}
		return action;
	}
	
	/**
	 * Mémorisation de l'interaction
	 * @param interaction : {@link Interaction} à ajouter à la mémoire
	 */
	public void memorize(Interaction interaction) {
		InteractionComposite compo = lastInteractionComposite();
		if(compo == null) {
			compo = new InteractionComposite(interaction);
			memories.add(compo);
		} else {
			InteractionComposite compoNext;
			if(!compo.hasPostInteraction()) {
				compo.setPostInteraction(interaction);
				compoNext = new InteractionComposite(compo.getPostInteraction());
				memories.add(compoNext);
			} else {
				// CAS DE FIGURE DEBUGUAGE (ne devrait jamais arriver)
				compoNext = new InteractionComposite(compo.getPostInteraction());
				memories.add(compoNext);
			}
		}
	}
	
	/**
	 * Retourne les {@link Interaction} mémorisées
	 * @return les {@link Interaction} mémorisées
	 */
	public Set<Interaction> interactionMemorized() {
		Set<Interaction> interactions = new HashSet<>();
		for(InteractionComposite compo : memories) {
			Interaction preInteraction = compo.getPreInteraction();
			if(preInteraction != null) interactions.add(preInteraction);
			Interaction postInteraction = compo.getPostInteraction();
			if(postInteraction != null) interactions.add(postInteraction);
		}
		return interactions;
	}
	
	/**
	 * Retourne la dernière {@link InteractionComposite} mémorisée
	 * @return la dernière {@link InteractionComposite} mémorisée
	 */
	public InteractionComposite lastInteractionComposite() {
		InteractionComposite compo;
		if(memories.isEmpty()) compo = null;
		else {
			compo = (InteractionComposite) memories.get(memories.size()-1);
		}
		return compo;
		
	}
	
	public List<Action> actionsNotUsedLast() {
		Action[] actions = Action.values();
		List<Action> actionsUsed = new ArrayList<>();
		List<Action> actionsNotUsed = new ArrayList<>();
		for(InteractionComposite interaction : memories) {
			Interaction pre = interaction.getPreInteraction();
			if(pre != null) {
				Action action = pre.getAction();
				if(!actionsUsed.contains(action))
					actionsUsed.add(action);
			}
		}
		for(Action action : actions) {
			if(!actionsUsed.contains(action))
				actionsNotUsed.add(action);
		}
		return actionsNotUsed;
	}
	
	public Action getLastPositiveAction() {
		Action action = null;
		int value;
		if(!memories.isEmpty()) {
			Interaction it = memories.get(0).getPreInteraction();
			action = it.getAction();
			value = it.getValue();
			for(InteractionComposite interaction : memories) {
				Interaction pre = interaction.getPreInteraction();
				if(pre != null && pre.getValue() > value) {
					action = pre.getAction();
					value = pre.getValue();
				}
				Interaction post = interaction.getPostInteraction();
				if(post != null && post.getValue() > value) {
					action = post.getAction();
					value = post.getValue();
				}
			}
		}
		return action;
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
	
	private static InteractionComposite bestInteractionComposite(List<InteractionComposite> interactions) {
		if(!interactions.isEmpty()) {
			InteractionComposite bestInteraction = interactions.get(0);
			for(InteractionComposite interaction : interactions) {
				if(interaction.getValue() > bestInteraction.getValue())
					bestInteraction = interaction;
			}
			return bestInteraction;
		} else
			return null;
	}
	
	private static Set<Action> actionsMemorized(Set<Interaction> interactions) {
		Set<Action> actions = new HashSet<>();
		for(Interaction interaction : interactions) {
			Action action = interaction.getAction();
			actions.add(action);
		}
		return actions;
	}
}
