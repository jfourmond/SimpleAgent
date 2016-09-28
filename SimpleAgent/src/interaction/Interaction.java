package interaction;

import action.Action;
import result.Result;

public class Interaction {
	private Action action;
	private Result result;
	private int value;
	
	
	//	CONSTRUCTEURS
	public Interaction() {
		action = null;
		result = null;
		value = 0;
	}
	
	public Interaction(Action action, Result result) {
		this.action = action;
		this.result = result;
		this.value = 0;
	}
	
	public Interaction(Action action, Result result, int value) {
		this.action = action;
		this.result = result;
		this.value = value;
	}
	
	//	GETTERS
	public Action getAction() { return action; }
	
	public Result getResult() { return result; }
	
	public int getValue() { return value; }
	
	//	SETTERS
	public void setAction(Action action) { this.action = action; }
	
	public void setResult(Result result) { this.result = result; }
	
	public void setValue(int value) { this.value = value; }
	
	//	METHODES
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		else {
			Interaction interaction = (Interaction) obj;
			return (action == interaction.action &&
					result == interaction.result &&
					value == interaction.value);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder ch = new StringBuilder("Interaction : \n");
		ch.append("\t" + action + "\n");
		ch.append("\t" + result + "\n");
		ch.append("\tValue : " + value + "\n");
		return ch.toString();
	}
}
