package persistence;

import java.io.Serializable;

public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3511585483221896173L;

	private long idCandleType = 0;

	// header
	private String nomeArquivo;
	private String dataArquivo;

	/* Tipo de registro */
	private String tipreg;

	/* Tipo de registro */
	private String dataPregao;
	// private String datadopregao;

	/* Codigo BDI */
	private String codbdi;

	/* Codigo de negocia�ao do papel */
	private String codneg;

	/* Tipo de Mercado */
	private String tpmerc;

	/* Nome resumido da empresa emissora do papel */
	private String nomres;

	/* Especifica�ao do papel */
	private String especi;

	/* Prazo em dias do mercado a termo */
	private String prazot;

	/* Moeda de referencia */
	private String modref;

	/* Pre�o de abertura do papel - Mercado no Pregao */
	private String preabe;

	/* Pre�o maximo do papel - Mercado no Pregao */
	private String premax;

	/* Pre�o minimo do papel - Mercado no Pregao */
	private String premin;

	/* Pre�o medio do papel - Mercado no Pregao */
	private String premed;

	/* Pre�o do ultimo negocio do papel - Mercado no Pregao */
	private String preult;

	/* Pre�o da melhor oferta de compra do papel - Mercado */
	private String preofc;

	/* Pre�o da melhor oferta de venda do papel - Mercado */
	private String preofv;

	/* Numero de negocios efetuados com o papel - Mercado */
	private String totneg;

	/* Quantidade total de titulos negociados neste papel - Mercado */
	private String quatot;

	/* Volume total de titulos negociados neste papel - Mercado */
	private String voltot;

	/*
	 * Pre�o de exercicio para o mercado de op�oes ou o valor do contrato para o
	 * mercado de termo secundario
	 */
	private String preexe;

	/*
	 * Indicador de corre�ao de pre�os de exercicios ou valores de contrato para
	 * os mercados de op�oes
	 */
	private String indopc;

	/* Data do vencimento para os mercados de op�oes ou termo secundario */
	private String datven;

	/* Fator de cota�ao do papel */
	private String fatcot;

	/*
	 * Pre�os de exercicio em pontos para op�oes referenciadas em dolar ou valor
	 * de contrato em pontos para termo secundario
	 */
	private String ptoexe;

	/* Codigo do papel no sistema ISIN ou codigo interno do papel */
	private String codisi;

	/* Numero de distribui�ao do papel */
	private String dismes;

	public String getCodbdi() {
		return codbdi;
	}

	/* ==================== GET & SET ==================== */

	public String getCodisi() {
		return codisi;
	}

	public String getCodneg() {
		return codneg;
	}

	public String getDataArquivo() {
		return dataArquivo;
	}

	public String getDataPregao() {
		return dataPregao;
	}

	public String getDatven() {
		return datven;
	}

	public String getDismes() {
		return dismes;
	}

	public String getEspeci() {
		return especi;
	}

	public String getFatcot() {
		return fatcot;
	}

	/*
	 * public String getDatadopregao() { return datadopregao; }
	 * 
	 * public void setDatadopregao(String datadopregao) { this.datadopregao =
	 * datadopregao; }
	 */
	public long getIdCandleType() {
		return idCandleType;
	}

	public String getIndopc() {
		return indopc;
	}

	public String getModref() {
		return modref;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public String getNomres() {
		return nomres;
	}

	public String getPrazot() {
		return prazot;
	}

	public double getPreabe() {
		return parseDouble(preabe);
	}

	public String getPreexe() {
		return preexe;
	}

	public double getPremax() {
		return parseDouble(premax);
	}

	public String getPremed() {
		return premed;
	}

	public double getPremin() {
		return parseDouble(premin);
	}

	public String getPreofc() {
		return preofc;
	}

	public String getPreofv() {
		return preofv;
	}

	public double getPreult() {
		return parseDouble(preult);
	}

	public String getPtoexe() {
		return ptoexe;
	}

	public String getQuatot() {
		return quatot;
	}

	public String getTipreg() {
		return tipreg;
	}

	public String getTotneg() {
		return totneg;
	}

	public String getTpmerc() {
		return tpmerc;
	}

	public String getVoltot() {
		return voltot;
	}

	private double parseDouble(String price) {
		String tmp = "";
		String value = String.valueOf(price);
		// System.out.println("price: " + price);
		if (value.indexOf(".") == -1) {
			tmp = value.substring(0, value.length() - 2) + "."
					+ value.substring(value.length() - 2, value.length());
		} else {
			tmp = value;
		}
		// System.out.println(tmp);
		return Double.parseDouble(tmp);
	}

	public void setCodbdi(String codbdi) {
		this.codbdi = codbdi;
	}

	public void setCodisi(String codisi) {
		this.codisi = codisi;
	}

	public void setCodneg(String codneg) {
		this.codneg = codneg;
	}

	public void setDataArquivo(String dataArquivo) {
		this.dataArquivo = dataArquivo;
	}

	public void setDataPregao(String dataPregao) {
		this.dataPregao = dataPregao;
	}

	public void setDatven(String datven) {
		this.datven = datven;
	}

	public void setDismes(String dismes) {
		this.dismes = dismes;
	}

	public void setEspeci(String especi) {
		this.especi = especi;
	}

	public void setFatcot(String fatcot) {
		this.fatcot = fatcot;
	}

	public void setIdCandleType(long idCandleType) {
		this.idCandleType = idCandleType;
	}

	public void setIndopc(String indopc) {
		this.indopc = indopc;
	}

	public void setModref(String modref) {
		this.modref = modref;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public void setNomres(String nomres) {
		this.nomres = nomres;
	}

	public void setPrazot(String prazot) {
		this.prazot = prazot;
	}

	public void setPreabe(String preabe) {
		this.preabe = preabe;
	}

	public void setPreexe(String preexe) {
		this.preexe = preexe;
	}

	public void setPremax(String premax) {
		this.premax = premax;
	}

	public void setPremed(String premed) {
		this.premed = premed;
	}

	public void setPremin(String premin) {
		this.premin = premin;
	}

	public void setPreofc(String preofc) {
		this.preofc = preofc;
	}

	public void setPreofv(String preofv) {
		this.preofv = preofv;
	}

	public void setPreult(String preult) {
		this.preult = preult;
	}

	public void setPtoexe(String ptoexe) {
		this.ptoexe = ptoexe;
	}

	public void setQuatot(String quatot) {
		this.quatot = quatot;
	}

	public void setTipreg(String tipreg) {
		this.tipreg = tipreg;
	}

	public void setTotneg(String totneg) {
		this.totneg = totneg;
	}

	public void setTpmerc(String tpmerc) {
		this.tpmerc = tpmerc;
	}

	public void setVoltot(String voltot) {
		this.voltot = voltot;
	}

}
