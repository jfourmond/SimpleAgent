package coupling;

import interaction.Interaction;
import result.Result;

/**
 * Système motivationnel 1
 * mot3: v(i11) = v(i21) = 1, v(i12) = v(i22) = -1
 * @author Jérôme
 */
public class Coupling3 implements Coupling {
	
	@Override
	public void motivate(Interaction interaction) {
		Result result = interaction.getResult();
		int value = 0;
		
		if(result == Result.WHITE) value = -1;
		else if(result == Result.GREEN) value = 1;
		
		interaction.setValue(value);
	}
}
