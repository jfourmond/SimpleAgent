package interaction;

import action.Action;
import result.Result;

public class InteractionComposite extends Interaction {
	private Interaction preInteraction;
	private Interaction postInteraction;
	
	//	CONSTRUCTEURS
	public InteractionComposite() {
		super();
		preInteraction = null;
		postInteraction = null;
	}
	
	public InteractionComposite(Action action, Result result) {
		super(action, result);
		
		preInteraction = null;
		postInteraction = null;
	}
	
	public InteractionComposite(Interaction preInteraction) {
		super();
		
		this.preInteraction = preInteraction;
	}
	
	//	GETTERS
	public Interaction getPreInteraction() { return preInteraction; }
	
	public Interaction getPostInteraction() { return postInteraction; }
	
	//	SETTERS
	public void setPreInteraction(Interaction preInteraction) {
		this.preInteraction = preInteraction;
	}
	
	public void setPostInteraction(Interaction postInteraction) {
		this.postInteraction = postInteraction;
	}
	
	// METHODES
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
