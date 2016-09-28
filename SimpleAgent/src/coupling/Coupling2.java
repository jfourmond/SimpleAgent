package coupling;

import action.Actions;
import interaction.Interaction;
import result.Results;

/**
 * Système motivationnel 2
 * mot2: v(i11) = v(i12) = -1, v(i21) = v(i22) = 1
 * @author Jérôme
 */
public class Coupling2 implements Coupling {
	@Override
	public void motivate(Interaction interaction) {
		Actions action = interaction.getAction().getAction();
		Results result = interaction.getResult().getResult();
		
		int value = 0;
		
		if(action == Actions.TRIANGLE && result == Results.WHITE) value = -1;
		else if(action == Actions.TRIANGLE && result == Results.GREEN) value = -1;
		else if(action == Actions.CIRCLE && result == Results.WHITE) value = 1;
		else if(action == Actions.CIRCLE && result == Results.GREEN) value = 1;
		
		interaction.setValue(value);
	}

}
