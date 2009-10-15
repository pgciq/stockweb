package ai.environment;

import ai.Percept;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public interface EnvironmentView {

	public Percept envChanged(String command);

}
