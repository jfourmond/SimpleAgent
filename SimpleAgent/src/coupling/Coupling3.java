package coupling;

import interaction.Interaction;
import result.Result;

// mot2: v(i11) = v(i21) = 1, v(i12) = v(i22) = -1

public class Coupling3 implements Coupling {
	
	@Override
	public void motivate(Interaction interaction) {
		Result result = interaction.getResult();
		int value = 0;
		
		if(result == Result.WHITE) value = 0;
		else if(result == Result.GREEN) value = 1;
		
		interaction.setValue(value);
	}
}
