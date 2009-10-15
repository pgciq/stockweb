package ai.agent;

import org.json.JSONObject;

import ai.Agent;
import ai.Percept;
import ai.agent.program.KeltnerBandsAP;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class KeltnerBandsAgent extends Agent{

	public KeltnerBandsAgent() {
		super(new KeltnerBandsAP());
	}
	
	public String execute(Percept p, String modal) {
		String result = super.execute(p, modal);
		result = (modal.equals("web"))? resultIndex(p, result) : valuated(p, result);
		return result;
	}

	//Retorna apenas o valor do indicador
	public String resultIndex(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		return ((KeltnerBandsAP)super.program).getADX();
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		String rsADX = ((KeltnerBandsAP)super.program).getADX();
		JSONObject json = new JSONObject(rsADX);

		System.out.println("ADX = " + json.getString("adx"));
		System.out.println("+D1 = " + json.getString("+D1"));

		if(json.getDouble("+D2") >= json.getDouble("-D2"))
			if(json.getDouble("+D1") <= json.getDouble("-D1"))
				return StatusAgent.Sell.toString();
				
		if(json.getDouble("+D2") <= json.getDouble("-D2"))
			if(json.getDouble("+D1") >= json.getDouble("-D1"))
				return StatusAgent.Buy.toString();

		
		return StatusAgent.Hold.toString();
	}

}
