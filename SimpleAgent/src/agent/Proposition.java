package agent;

import action.Action;

public class Proposition implements Comparable<Proposition>{
	private Action action;
	private int proclivity;
	
	//	CONSTRUCTEURS
	public Proposition(Action action, int proclivity) {
		this.action = action;
		this.proclivity = proclivity;
	}
	
	//	GETTERS
	public Action getAction() { return action; }
	
	public int getProclivity() { return proclivity; }
	
	//	SETTERS
	public void setAction(Action action) { this.action = action; }
	
	public void setProclivity(int proclivity) { this.proclivity = proclivity; }
	
	//	METHODES
	
	public void addProclivity(int proclivity) {
		this.proclivity += proclivity;
	}
	
	@Override
	public int compareTo(Proposition proposition) {
		return new Integer(proposition.getProclivity()).compareTo(proclivity);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		else {
			Proposition proposition = (Proposition) obj;
			return (proposition.getAction() == action);
		}
	}
}
