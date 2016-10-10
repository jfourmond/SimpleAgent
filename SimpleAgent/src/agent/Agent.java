package agent;

import java.util.ArrayList;
import java.util.List;

import action.Action;
import interaction.Interaction;
import interaction.InteractionComposite;
import result.Result;

/**
 * Classe abstraite représentant un Agent
 * @author Jérôme
 */
public abstract class Agent {
	protected List<InteractionComposite> memories;
	protected Interaction lastInteraction;
	
	protected int cycle;
	
	protected int actionCount;
	
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
	/**
	 * L'agent choisit l'action à effectuer en fonction de son expérience
	 * @param R : {@link Result}
	 * @return l'{@link Action} que l'agent a choisit d'après son expériene
	 */
	public abstract Action chooseAction(Result R);
	
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
			if(preInteraction.getAction() == preAction && postInteraction.getAction() == postAction) {
				if(preAction == postAction && !preInteraction.equals(postInteraction))
					return false;
				else return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder ch = new StringBuilder();
		ch.append("Agent (Age " + cycle + ") : \n");
		ch.append("Memories : " + memories + "\n");
		return ch.toString();
	}
}
