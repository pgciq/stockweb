package ai.agent;

import chart.study.QuoteHistory;

import ai.Agent;
import ai.Percept;
import ai.agent.program.BandsBollingerAP;
import ai.agent.program.EMAAP;
import ai.agent.program.NoiseAdjustedRSIAP;
import ai.agent.program.WilliamPercRAP;

public class WilliamPercRAgent extends Agent{

	public WilliamPercRAgent() {
		super(new WilliamPercRAP());
	}
	
	public String execute(Percept p, String modal) {
		String result = super.execute(p, modal);
		result = (modal.equals("web")) ? resultIndex(p, result) : valuated(p, result);
		return result;
	}

	//Retorna apenas o valor do indicador
	public String resultIndex(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		QuoteHistory history = ((WilliamPercRAP)super.program).history;
		String percRLast = String.valueOf(((WilliamPercRAP)super.program).getPercRLast());
		String percRPrev = String.valueOf(((WilliamPercRAP)super.program).getPercRPreview());
		return "{type:william%r, percRPrev:" + percRPrev + ", percRLast:" + percRLast + ", date:"+history.getLastPriceBar().getDate()+"}";
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		double percRPrev, percRLast;
		percRLast = ((WilliamPercRAP)super.program).getPercRLast();
		percRPrev = ((WilliamPercRAP)super.program).getPercRPreview();
		
		QuoteHistory history = ((WilliamPercRAP)super.program).history;

		String status = applyRole(percRPrev, percRLast);
		int count = 1;
		while(status.equals(StatusAgent.Medio.toString())){
			percRPrev = ((WilliamPercRAP)super.program).searchTrend(count);
			status = applyRole(percRPrev, percRLast);
			count++;
		}
		
		return status.toString() + "|" + count;

	}


	// ---------------------- BEGIN DEBUG ----------------------

	public String executeForDebug(QuoteHistory history) {
		return valuatedDebug(history);		
	}
	
	public String valuatedDebug(QuoteHistory history){

		double percRPrev, percRLast;
		percRLast = ((WilliamPercRAP)super.program).getPercRLast(history);
		percRPrev = ((WilliamPercRAP)super.program).getPercRPreview(history);

		String status = applyRole(percRPrev, percRLast);
		int count = 1;
		System.out.println("--------------------");
		while(status.equals(StatusAgent.Medio.toString())){
			percRPrev = ((WilliamPercRAP)super.program).searchTrend(history, count);
			status = applyRole(percRPrev, percRLast);
			count++;
			System.out.println(count + " --------------------");
		}

		return status.toString() + "|" + count;

	}
	// ---------------------- END DEBUG ------------------------

	private String applyRole(double percRPrev, double percRLast) {
		System.out.println("%R Last = " + percRLast);
		System.out.println("%R Prev = " + percRPrev);
		
		if((percRPrev > percRLast) && (percRPrev >= 65) && (percRLast <= 65 ))
			return StatusAgent.Alto.toString();
		
		if((percRPrev < percRLast) && (percRPrev < 65) && (percRLast >= 65 ))
			return StatusAgent.Baixo.toString();
		
		return StatusAgent.Medio.toString();
	}

}
