package ai.agent;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public enum StatusAgent {
	Buy("buy"),
	Hold("hold"),
	Sell("sell"),
	Alto("100"),
	MedioAlto("75"), 
	Medio("50"), 
	MedioBaixo("25"), 
	Baixo("0");

	private StatusAgent(String param) {
		this.param = param;
	}

	public String toString() {
		return param;
	}

	private final String param;

}
