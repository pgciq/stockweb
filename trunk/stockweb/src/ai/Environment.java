package ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ai.environment.EnvironmentView;
import ai.environment.YahooEnvironmentView;

/**
 * Artificial Intelligence A Modern Approach (2nd Edition): page 32.
 * 
 * Environment.
 */

/**
 * @author Ravi Mohan
 * 
 */

public class Environment {

	//protected ArrayList<EnvironmentObject> objects;

	protected ArrayList<Agent> agents;

	protected ArrayList<EnvironmentView> views;

	protected Percept percept = new Percept();
	
	private HashMap<String, String> mapResponseAgents = new HashMap<String, String>();
	
	public String getResponseAgents(String agentName){
		return mapResponseAgents.get(agentName);
	}
	
	public void executeAction(Agent a, String act){
		//System.out.println(act);
		mapResponseAgents.put(a.getClass().getSimpleName(), act);
	}

	public Percept getPerceptSeenBy(EnvironmentView env){
		return (Percept) percept.getAttribute(env.getClass().getSimpleName());
	}

	public Environment() {
		agents = new ArrayList<Agent>();
		views = new ArrayList<EnvironmentView>();
		//objects = new ArrayList<EnvironmentObject>();
	}

	public void registerView(EnvironmentView bev) {
		views.add(bev);
	}

	public void updateViews(String command) {
		Percept percept;
		for (EnvironmentView view : views) {
			percept = view.envChanged(command);
			if(percept.getAttribute("change").equals("false")) // NOTE 02
				percept = null;

			this.percept.setAttribute(view.getClass().getSimpleName(), percept);  // NOTE 01
		}
	}

	public boolean isDone() {

		for (Agent agent : agents) {
			if (agent.isAlive()) {
				return false;
			}
		}

		return true;
	}

	public void createExogenousChange() {

	}

	public void step(String modal) {
		if (!(this.isDone())) {

			for (EnvironmentView view : views) {
				if(this.getPerceptSeenBy(view) != null)
					for (Agent agent : agents) {
						String anAction = agent.execute(this.getPerceptSeenBy(view), modal);
//						updateViews(anAction);
						//Este metodo representa o ambiente que responde (reflete) devido a açao do Agente
						this.executeAction(agent, anAction);
						/* Sendo que o Agente nao interfere no ambiente (agente=candlestick e ambiente=cotaçao dos preços)
						 * O ambiente pode ficar como responsavel em manter os resultados das açoes dos Agentes
						 */
						
					}
			}
			this.createExogenousChange();
		}
	}
/*
	public void step(int n) {

		for (int i = 0; i < n; i++) {

			step();

		}
	}

	public void stepUntilNoOp() {
		while (!(this.isDone())) {
			step();
		}
	}
*/
	public ArrayList getAgents() {
		return agents;
	}

/*	public ArrayList getObjects() {
		return objects;
	}

	public boolean alreadyContains(EnvironmentObject o) {

		boolean retval = false;

		for (EnvironmentObject eo : objects) {
			if (eo.equals(o)) {
				retval = true;
			}
		}

		return retval;
	}
*/
	public boolean alreadyContains(Agent anAgent) {
		boolean retval = false;
		for (Agent agent : agents) {
			if (agent.equals(anAgent)) {
				retval = true;
			}
		}
		return retval;
	}

	public void addAgent(Agent a, String attributeName, Object attributeValue) {
		if (!(alreadyContains(a))) {
			a.setAttribute(attributeName, attributeValue);
			agents.add(a);
		}
	}
/*
	public void addObject(EnvironmentObject o, String attributeName,
			Object attributeValue) {
		if (!(alreadyContains(o))) {
			o.setAttribute(attributeName, attributeValue);
			objects.add(o);
		}
	}

	public void addObject(EnvironmentObject o) {
		if (!(alreadyContains(o))) {
			objects.add(o);
		}
	}
*/
	public void addAgent(Agent a) {
		if (!(alreadyContains(a))) {
			agents.add(a);
		}
	}

	public List<ObjectWithDynamicAttributes> getAllObjects() {
		List<ObjectWithDynamicAttributes> l = new ArrayList<ObjectWithDynamicAttributes>();
//		l.addAll(objects);
		l.addAll(agents);
		return l;
	}

}

/*
 NOTE 01: Cada ambiente possui uma percepçao, como o ser humano, a visao quando percebe uma mudança no seu ambiente, é registrado 
 na memoria que como a "visao percebeu determinada mudança de ambiente", quando nos queimamos, "nosso tato percebeu que naquele lugar é quente"
 O mesmo é aplicado para EnvironmentView, para cada vez que ocorre uma mudança em qualquer ambiente, sua percepçao é registrado na memoria do mesmo.
 
 NOTE 02: E' necessario verificar se houve ou nao mudança no ambiente antes de acionar os Agentes. 
 Foi criado um atributo "change" que cada Environment seta se houve ou nao mudança no ambiente.
 
  
 */

