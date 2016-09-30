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
	private Random rand;
	
	//	CONSTRUCTEURS
	public Agent() {
		memories = new ArrayList<>();
		cycle = 0;
		actionCount = 0;
		
		rand = new Random();
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
		InteractionComposite compo;
		if(lastInteraction != null) {
			compo = new InteractionComposite(lastInteraction, interaction);
			if(!memories.contains(compo)) memories.add(compo);
		}
		lastInteraction = interaction;
	}
	
	public Action randAction(Action[] actions) {
		// TODO C'est nul le random...
		// TODO Vérifier si l'action a déjà été choisi, mais ne permet pas une interaction activée
		System.out.println("Pas activé");
		int n = rand.nextInt(actions.length);
		return actions[n];
	}
	
	public List<InteractionComposite> activatedInteractionComposite(Interaction interaction) {
		List<InteractionComposite> interactionsComposites = new ArrayList<>();
		for(InteractionComposite IC : memories) {
			if(IC.active(interaction))
				interactionsComposites.add(IC);
		}
		return interactionsComposites;
	}
	
	@Override
	public String toString() {
		StringBuilder ch = new StringBuilder();
		ch.append("Agent (Age " + cycle + ") : \n");
		ch.append("Memories : " + memories + "\n");
		return ch.toString();
	}
}
