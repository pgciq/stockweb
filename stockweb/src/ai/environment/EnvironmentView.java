package ai.environment;

import ai.Percept;

public interface EnvironmentView {
	
	public Percept envChanged(String command);
	
	
}
