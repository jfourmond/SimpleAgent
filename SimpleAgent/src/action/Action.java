package action;

public class Action {
	private Actions action;
	
	//	CONSTRUCTEURS
	public Action() { action = null; }
	
	public Action(Actions action) { this.action = action; }
	
	//	GETTERS
	public Actions getAction() { return action; }
	
	//	SETTERS
	public void setAction(Actions action) { this.action = action; }
	
	//	METHODES
	@Override
	public String toString() {
		return "Action : " + action;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		else {
			Action action = (Action) obj;
			return (this.action.equals(action.getAction()));
		}
	}
}
