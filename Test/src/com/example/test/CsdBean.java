package com.example.test;

import java.io.Serializable;
import java.util.Date;

public class CsdBean implements Serializable {

	private static final long serialVersionUID = 8166187197911698097L;

	private String numeroSerie;

	private Date validoDe;

	private Date validoHasta;

	public CsdBean(final String numeroSerie, final Date validoDe, final Date validoHasta) {
		this.numeroSerie = numeroSerie;
		this.validoDe = validoDe;
		this.validoHasta = validoHasta;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(final String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Date getValidoDe() {
		return validoDe;
	}

	public void setValidoDe(final Date validoDe) {
		this.validoDe = validoDe;
	}

	public Date getValidoHasta() {
		return validoHasta;
	}

	public void setValidoHasta(final Date validoHasta) {
		this.validoHasta = validoHasta;
	}

}
