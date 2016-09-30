package interaction;

import action.Action;

public class InteractionComposite {
	private Interaction preInteraction;
	private Interaction postInteraction;
	
	private int value;
	private int weight;
	
	//	CONSTRUCTEURS
	public InteractionComposite() {
		preInteraction = null;
		postInteraction = null;
		
		calculateValue();
		weight = 0;
	}
	
	public InteractionComposite(Interaction preInteraction) {
		this.preInteraction = preInteraction;
		
		calculateValue();
		weight = 0;
	}
	
	public InteractionComposite(Interaction preInteraction, Interaction postInteraction) {
		this.preInteraction = preInteraction;
		this.postInteraction = postInteraction;
		
		calculateValue();
		weight = 0;
	}
	
	//	GETTERS
	public Interaction getPreInteraction() { return preInteraction; }
	
	public Interaction getPostInteraction() { return postInteraction; }
	
	public int getValue() { return value; }
	
	public int getWeight() { return weight; }
	
	//	SETTERS
	public void setPreInteraction(Interaction preInteraction) {
		this.preInteraction = preInteraction;
		calculateValue();
	}
	
	public void setPostInteraction(Interaction postInteraction) {
		this.postInteraction = postInteraction;
		calculateValue();
	}
	
	public void setValue(int value) { this.value = value; }
	
	public void setWeight(int weight) { this.weight = weight; }
	
	// METHODES
	public boolean hasPreInteraction() { return preInteraction != null; }
	
	public boolean hasPostInteraction() { return postInteraction != null; }
	
	public boolean isFull() {
		return (hasPreInteraction() && hasPostInteraction());
	}
	
	public void calculateValue() {
		if(preInteraction != null && postInteraction != null)
			value = preInteraction.getValue() + postInteraction.getValue();
		else if(preInteraction != null && postInteraction == null)
			value = preInteraction.getValue();
		else if(preInteraction == null && postInteraction != null)
			value = postInteraction.getValue();
		else if(preInteraction == null && postInteraction == null)
			value = 0;
	}
	
	public Action bestAction() {
		if(preInteraction != null && postInteraction != null)
			if(preInteraction.getValue() > postInteraction.getValue())
				return preInteraction.getAction();
			else
				return postInteraction.getAction();
		else if(preInteraction != null && postInteraction == null)
			return preInteraction.getAction();
		else if(preInteraction == null && postInteraction != null)
			return postInteraction.getAction();
		else if(preInteraction == null && postInteraction == null)
			return null;
		return null;
	}
	
	public boolean active(Interaction interaction) {
		return interaction.equals(preInteraction);
	}
	
	@Override
	public String toString() {
		StringBuilder ch = new StringBuilder();
		ch.append(value + " ");
		ch.append("[ " + preInteraction + " , " + postInteraction + " ]");
		return ch.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		else {
			InteractionComposite interactionComposite = (InteractionComposite) obj;
			return (preInteraction.equals(interactionComposite.preInteraction) &&
					postInteraction.equals(interactionComposite.postInteraction));
		}
	}
}
