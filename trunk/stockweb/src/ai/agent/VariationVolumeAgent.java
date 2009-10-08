package ai.agent;


import ai.Agent;
import ai.Percept;
import ai.agent.program.VariationVolumeAP;

public class VariationVolumeAgent extends Agent{

	public VariationVolumeAgent() {
		super(new VariationVolumeAP());
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

		return ((VariationVolumeAP)super.program).getListVariationVolume();
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";
		
		return ((VariationVolumeAP)super.program).getVariationVolume();
	}

}
