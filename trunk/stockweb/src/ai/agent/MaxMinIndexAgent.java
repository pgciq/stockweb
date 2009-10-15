package ai.agent;


import ai.Agent;
import ai.Percept;
import ai.agent.program.MaxMinIndexAP;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class MaxMinIndexAgent extends Agent{

	public MaxMinIndexAgent() {
		super(new MaxMinIndexAP());
	}
	
	public String execute(Percept p, String modal) {
		String result = super.execute(p, modal);
		if(modal.equals("web"))
			result = resultIndex(p, result);
		else
			result = valuated(p, result);
		
		return result;
	}	

	//Retorna apenas o valor do indicador
	public String resultIndex(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		return ((MaxMinIndexAP)super.program).getListMaxMinIndex();
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";
		
		return ((MaxMinIndexAP)super.program).getMaxMinIndex();

	}

}
