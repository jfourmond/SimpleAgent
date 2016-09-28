package coupling;

import action.Action;
import interaction.Interaction;
import result.Result;

/**
 * Système motivationnel 1
 * mot1: v(i11) = v(i12) = 1, v(i21) = v(i22) = -1
 * @author Jérôme
 */
public class Coupling1 implements Coupling {
	@Override
	public void motivate(Interaction interaction) {
		Action action = interaction.getAction();
		Result result = interaction.getResult();
		
		int value = 0;	// neutre
		
		if(action == Action.TRIANGLE && result == Result.WHITE) value = 1;
		else if(action == Action.TRIANGLE && result == Result.GREEN) value = 1;
		else if(action == Action.CIRCLE && result == Result.WHITE) value = -1;
		else if(action == Action.CIRCLE && result == Result.GREEN) value = -1;
		
		interaction.setValue(value);
	}
}
