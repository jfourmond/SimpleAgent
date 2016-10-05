package agent;

import java.util.ArrayList;
import java.util.List;

import action.Action;
import interaction.Interaction;
import interaction.InteractionComposite;
import result.Result;

public class Agent2 implements Agent {
	//	"Mémoire" de l'agent
	private List<InteractionComposite> memories;
	private Interaction lastInteraction;
	
	private int cycle;
	
	private int actionCount;
	
	//	CONSTRUCTEURS
	public Agent2() {
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
	 * Mémorisation de l'interaction
	 * @param interaction : {@link Interaction} à ajouter à la mémoire
	 */
	public InteractionComposite memorize(Interaction interaction) {
		InteractionComposite compo = null;
		if(lastInteraction != null) {
			compo = new InteractionComposite(lastInteraction, interaction, 1);
			if(!memories.contains(compo)) memories.add(compo);
			else {
				int n = memories.indexOf(compo);
				compo = memories.get(n);
				compo.reinforce();
			}
		}
		lastInteraction = interaction;
		cycle++;
		return compo;
	}
	
	/**
	 * Retourne une action qui n'est pas encore connue par l'agent
	 * @return une {@link Action} qui n'est pas encore connue par l' {@link Agent1}
	 */
	public Action randAction() {
		Action[] actions = Action.values();
		Action action = null;
		for(Action a : actions) {
			action = a;
			if(!knownComposite(lastInteraction.getAction(), action))
				return action;
		}
		return action;
	}
	
	/**
	 * Retourne une {@link List} des {@link InteractionComposite} activées pour l' {@link Interaction} passée en paramètre
	 * @param interaction : {@link Interaction} à tester l'activation sur la mémoire
	 * @return une {@link List} des {@link InteractionComposite} activées pour l' {@link Interaction} passée en paramètre
	 */
	public List<InteractionComposite> activatedInteractionComposite(Interaction interaction) {
		List<InteractionComposite> interactionsComposites = new ArrayList<>();
		for(InteractionComposite IC : memories) {
			if(IC.active(interaction))
				interactionsComposites.add(IC);
		}
		return interactionsComposites;
	}
	
	/**
	 * Teste s'il existe une {@link InteractionComposite} dont la préInteraction est la preAction passée en paramètre
	 * et dont la postAction est la postAction passée en paramètre
	 * @param preAction {@link Action} à rechercher
	 * @param postAction {@link Action} à rechercher
	 * @return <code>true</code> s'il existe une {@link InteractionComposite} dont la préInteraction est la preAction
	 * et dont la postAction est la postAction
	 * @return <code>false</code> sinon
	 */
	public boolean knownComposite(Action preAction, Action postAction) {
		for(InteractionComposite IC : memories) {
			Interaction preInteraction = IC.getPreInteraction();
			Interaction postInteraction = IC.getPostInteraction();
			if(preInteraction.getAction() == preAction && postInteraction.getAction() == postAction)
				return true;
		}
		return false;
	}
	
	private List<Proposition> buildPropositionsInteraction(List<InteractionComposite> composites) {
		List<Proposition> propositions = new ArrayList<>();
		for(InteractionComposite compo : composites) {
			for(Interaction interaction : compo.interactions())
				propositions.add(new Proposition(interaction.getAction(), compo.getWeight() * interaction.getValue()));
		}
		return propositions;
	}
	
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
	
	private int getFullProclivityAction(List<Proposition> propositions, Action action) {
		int n = 0;
		for(Proposition proposition : propositions) {
			if(proposition.getAction() == action)
				n += proposition.getProclivity();
		}
		return n;
	}

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
	
	@Override
	public String toString() {
		StringBuilder ch = new StringBuilder();
		ch.append("Agent (Age " + cycle + ") : \n");
		ch.append("Memories : " + memories + "\n");
		return ch.toString();
	}
}
