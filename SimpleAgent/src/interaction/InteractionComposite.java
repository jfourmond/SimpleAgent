package interaction;

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
	/**
	 * Teste si l'{@link InteractionComposite} courante possède une première {@link Interaction}
	 * @return <code>true</code> si l'{@link InteractionComposite} courante possède une première {@link Interaction}
	 * @return <code>false</code> sinon
	 */
	public boolean hasPreInteraction() { return preInteraction != null; }
	
	/**
	 * Teste si l'{@link InteractionComposite} courante possède une seconde {@link Interaction}
	 * @return <code>true</code> si l'{@link InteractionComposite} courante possède une seconde {@link Interaction}
	 * @return <code>false</code> sinon
	 */
	public boolean hasPostInteraction() { return postInteraction != null; }
	
	/**
	 * Teste si l'{@link InteractionComposite} courante est pleine, c-à-d possède une première et une seconde {@link Interaction}
	 * @return <code>true</code>  si l'{@link InteractionComposite} courante est pleine
	 * @return <code>false</code> sinon
	 */
	public boolean isFull() {
		return (hasPreInteraction() && hasPostInteraction());
	}
	
	/**
	 * Met à jour l'attribut valeur/valence de l'{@link InteractionComposite} courante
	 */
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
	
	/**
	 * Teste si l'{@link InteractionComposite} courante est active pour l'{@link Interaction} passée en paramètre
	 * @param interaction : à tester l'activation sur l'{@link InteractionComposite}
	 * @return <code>true</code> si l'{@link InteractionComposite} courante est active pour l'{@link Interaction} passée en paramètre
	 * @return <code>false</code> sinon
	 */
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
