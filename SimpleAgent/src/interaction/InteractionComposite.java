package interaction;

import action.Action;
import result.Result;

public class InteractionComposite {
	private Interaction preInteraction;
	private Interaction postInteraction;
	
	private int value;
	private int weight;
	
	//	CONSTRUCTEURS
	public InteractionComposite() {
		preInteraction = null;
		postInteraction = null;
		
		value = 0;
		weight = 0;
	}
	
	public InteractionComposite(Interaction preInteraction) {
		this.preInteraction = preInteraction;
		
		value = preInteraction.getValue();
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
	}
	
	public void setPostInteraction(Interaction postInteraction) {
		this.postInteraction = postInteraction;
	}
	
	public void setValue(int value) { this.value = value; }
	
	public void setWeight(int weight) { this.weight = weight; }
	
	// METHODES
	public boolean hasPreInteraction() { return preInteraction != null; }
	
	public boolean hasPostInteraction() { return postInteraction != null; }
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		else {
			boolean b = super.equals(obj);
			InteractionComposite interactionComposite = (InteractionComposite) obj;
			return (b &&
					preInteraction.equals(interactionComposite.preInteraction) &&
					postInteraction.equals(interactionComposite.postInteraction));
		}
	}
}
