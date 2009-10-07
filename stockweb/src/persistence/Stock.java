package persistence;

import java.io.Serializable;



public class Stock implements Serializable{

	private long idCandleType = 0;

	// header
	private String nomeArquivo; 
	private String dataArquivo; 
	
	
	/* Tipo de registro */
	private String tipreg;
	
	/* Tipo de registro */
	private String dataPregao;
//	private String datadopregao;
	
	/*Codigo BDI*/
	private String codbdi;
	
	/* Codigo de negociaçao do papel */
	private String codneg;

	/* Tipo de Mercado */
	private String tpmerc;
	
	/* Nome resumido da empresa emissora do papel */
	private String nomres;
	
	/* Especificaçao do papel*/
	private String especi;
	
	/* Prazo em dias do mercado a termo */
	private String prazot;
	
	/* Moeda de referencia */
	private String modref;
	
	/* Preço de abertura do papel - Mercado no Pregao */
	private String preabe;
	
	/* Preço maximo do papel - Mercado no Pregao */
	private String premax; 

	/* Preço minimo do papel - Mercado no Pregao */
	private String premin;
	
	/* Preço medio do papel - Mercado no Pregao */
	private String premed;

	/* Preço do ultimo negocio do papel - Mercado no Pregao */
	private String preult;
	
	/* Preço da melhor oferta de compra do papel - Mercado */
	private String preofc;

	/* Preço da melhor oferta de venda do papel - Mercado */
	private String preofv;

	/* Numero de negocios efetuados com o papel - Mercado */
	private String totneg;

	/* Quantidade total de titulos negociados neste papel - Mercado */
	private String quatot;

	/* Volume total de titulos negociados neste papel - Mercado */
	private String voltot;
	
	/* Preço de exercicio para o mercado de opçoes ou o valor do contrato para o mercado de termo secundario */
	private String preexe;
	
	/* Indicador de correçao de preços de exercicios ou valores de contrato para os mercados de opçoes */
	private String indopc;
	
	/* Data do vencimento para os mercados de opçoes ou termo secundario */
	private String datven;
	
	/* Fator de cotaçao do papel */
	private String fatcot;
	
	/* Preços de exercicio em pontos para opçoes referenciadas em dolar ou valor de contrato em pontos para termo secundario */
	private String ptoexe;
	
	/* Codigo do papel no sistema ISIN ou codigo interno do papel */
	private String codisi;
	
	/* Numero de distribuiçao do papel */
	private String dismes;

	private double parseDouble(String price){
		String tmp = "";
		String value = String.valueOf(price);
		//System.out.println("price: " + price);
		if(value.indexOf(".") == -1)
			tmp = value.substring(0, value.length()-2) + "." + value.substring(value.length()-2, value.length());
		else
			tmp = value;
//		System.out.println(tmp);
		return Double.parseDouble(tmp);
	}		

	/* ====================  GET & SET ==================== */
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getDataArquivo() {
		return dataArquivo;
	}

	public void setDataArquivo(String dataArquivo) {
		this.dataArquivo = dataArquivo;
	}

	public String getDataPregao() {
		return dataPregao;
	}

	public void setDataPregao(String dataPregao) {
		this.dataPregao = dataPregao;
	}

	public String getCodbdi() {
		return codbdi;
	}

	public void setCodbdi(String codbdi) {
		this.codbdi = codbdi;
	}

	public String getCodneg() {
		return codneg;
	}

	public void setCodneg(String codneg) {
		this.codneg = codneg;
	}

	public String getTpmerc() {
		return tpmerc;
	}

	public void setTpmerc(String tpmerc) {
		this.tpmerc = tpmerc;
	}

	public String getNomres() {
		return nomres;
	}

	public void setNomres(String nomres) {
		this.nomres = nomres;
	}

	public String getEspeci() {
		return especi;
	}

	public void setEspeci(String especi) {
		this.especi = especi;
	}

	public String getPrazot() {
		return prazot;
	}

	public void setPrazot(String prazot) {
		this.prazot = prazot;
	}

	public String getModref() {
		return modref;
	}

	public void setModref(String modref) {
		this.modref = modref;
	}

	public double getPreabe() {
		return parseDouble(preabe);
	}

	public void setPreabe(String preabe) {
		this.preabe = preabe;
	}

	public double getPremax() {
		return parseDouble(premax);
	}

	public void setPremax(String premax) {
		this.premax = premax;
	}

	public double getPremin() {
		return parseDouble(premin);
	}

	public void setPremin(String premin) {
		this.premin = premin;
	}

	public String getPremed() {
		return premed;
	}

	public void setPremed(String premed) {
		this.premed = premed;
	}

	public double getPreult() {
		return parseDouble(preult);
	}

	public void setPreult(String preult) {
		this.preult = preult;
	}

	public String getPreofc() {
		return preofc;
	}

	public void setPreofc(String preofc) {
		this.preofc = preofc;
	}

	public String getPreofv() {
		return preofv;
	}

	public void setPreofv(String preofv) {
		this.preofv = preofv;
	}

	public String getTotneg() {
		return totneg;
	}

	public void setTotneg(String totneg) {
		this.totneg = totneg;
	}

	public String getQuatot() {
		return quatot;
	}

	public void setQuatot(String quatot) {
		this.quatot = quatot;
	}

	public String getVoltot() {
		return voltot;
	}

	public void setVoltot(String voltot) {
		this.voltot = voltot;
	}

	public String getPreexe() {
		return preexe;
	}

	public void setPreexe(String preexe) {
		this.preexe = preexe;
	}

	public String getIndopc() {
		return indopc;
	}

	public void setIndopc(String indopc) {
		this.indopc = indopc;
	}

	public String getDatven() {
		return datven;
	}

	public void setDatven(String datven) {
		this.datven = datven;
	}

	public String getFatcot() {
		return fatcot;
	}

	public void setFatcot(String fatcot) {
		this.fatcot = fatcot;
	}

	public String getPtoexe() {
		return ptoexe;
	}

	public void setPtoexe(String ptoexe) {
		this.ptoexe = ptoexe;
	}

	public String getCodisi() {
		return codisi;
	}

	public void setCodisi(String codisi) {
		this.codisi = codisi;
	}

	public String getDismes() {
		return dismes;
	}

	public void setDismes(String dismes) {
		this.dismes = dismes;
	}


	public String getTipreg() {
		return tipreg;
	}

	public void setTipreg(String tipreg) {
		this.tipreg = tipreg;
	}

/*	public String getDatadopregao() {
		return datadopregao;
	}

	public void setDatadopregao(String datadopregao) {
		this.datadopregao = datadopregao;
	}
*/	
	public long getIdCandleType() {
		return idCandleType;
	}

	public void setIdCandleType(long idCandleType) {
		this.idCandleType = idCandleType;
	}	

	
	
	
	

	
	
	

}
