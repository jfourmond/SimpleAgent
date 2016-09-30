package agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import action.Action;
import interaction.Interaction;
import interaction.InteractionComposite;
import result.Result;

public class Agent {
	//	"Mémoire" de l'agent
	private List<InteractionComposite> memories;
	private Interaction lastInteraction;
	
	private int cycle;
	private int actionCount;
	
	//	CONSTRUCTEURS
	public Agent() {
		memories = new ArrayList<>();
		cycle = 0;
		actionCount = 0;
	}
	
	//	GETTERS
	public List<InteractionComposite> getMemories() { return memories; }
	
	public Interaction getLastInteraction() { return lastInteraction; }
	
	public int getCycle() { return cycle; }
	
	//	SETTERS
	public void setMemories(List<InteractionComposite> memories) { this.memories = memories; }
	
	public void setLastInteraction(Interaction lastInteraction) { this.lastInteraction = lastInteraction; }
	
	public void setCycle(int cycle) { this.cycle = cycle; }
	
	//	METHODES
	public Action chooseAction(Result result) {
		// TODO Revoir code, revoir raisonnement (deuxième if)
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
					action = randAction(actions);
				} else {
					for(InteractionComposite compos : activatedComposites) {
						Interaction post = compos.getPostInteraction();
						if(post.getValue() > 0) action = post.getAction();
					}
					if(action == null) {
						// Aucune interaction ne convient donc...
						action = randAction(actions);
					}
				}
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
		if(compo == null) {	// La première interaction composite
			compo = new InteractionComposite(interaction);
			memories.add(compo);
		} else {
			if(!compo.hasPostInteraction())
				compo.setPostInteraction(interaction);
			else {
				InteractionComposite compoNext = new InteractionComposite(lastInteraction(), interaction);
				if(!memories.contains(compoNext)) memories.add(compoNext);
			}
		}
	}
	
	public Action randAction(Action[] actions) {
		// TODO C'est nul le random...
		System.out.println("Pas activé");
		Random rand = new Random();
		int n = rand.nextInt(actions.length);
		return actions[n];
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
	
	/**
	 * Retourne la meilleure {@link InteractionComposite} mémorisée
	 * @return la meilleure {@link InteractionComposite} mémorisée
	 */
	public InteractionComposite bestInteractionComposite() {
		InteractionComposite best = null;
		if(!memories.isEmpty()) {
			best = memories.get(0);
			for(InteractionComposite compo : memories)
				if(compo.getValue() > best.getValue()) best = compo;
		}
		return best;
	}
	
	public InteractionComposite lastFullInteractionComposite() {
		InteractionComposite best = null;
		if(!memories.isEmpty()) {
			best = memories.get(0);
			InteractionComposite compo;
			for(int i=0 ; i<memories.size() ; i++) {
				compo = memories.get(i);
				if(compo.hasPostInteraction() && compo.getValue() > best.getValue())
					best = compo;
			}
				
		}
		return best;
	}
	
	/**
	 * Retourne la dernière {@link Interaction} mémorisée
	 * @return la dernière {@link Interaction} mémorisée
	 */
	public Interaction lastInteraction() {
		Interaction interaction = null;
		if(!memories.isEmpty()) {
			InteractionComposite interactionComposite = lastInteractionComposite();
			if(interactionComposite.hasPostInteraction())
				interaction = interactionComposite.getPostInteraction();
			else interaction = interactionComposite.getPreInteraction();
		}
		return interaction;
	}
	
	public List<InteractionComposite> activatedInteractionComposite(Interaction interaction) {
		List<InteractionComposite> interactionsComposites = new ArrayList<>();
		for(InteractionComposite IC : memories) {
			if(IC.active(interaction))
				interactionsComposites.add(IC);
		}
		return interactionsComposites;
	}
}
