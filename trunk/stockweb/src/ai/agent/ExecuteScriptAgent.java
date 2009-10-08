package ai.agent;


import ai.Agent;
import ai.Percept;
import ai.agent.program.ExecuteScriptAP;

public class ExecuteScriptAgent extends Agent{

	public ExecuteScriptAgent() {
		super(new ExecuteScriptAP());
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

		return ((ExecuteScriptAP)super.program).getListResultScript();
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";
		
		return ((ExecuteScriptAP)super.program).getResultScript();
	}

}
