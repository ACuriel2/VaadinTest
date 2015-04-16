package com.example.test;

import java.util.Date;

public class CsdBean {
	
	private String numeroSerie;
	
	private Date validoDe;
	
	private Date validoHasta;
	
	public CsdBean(final String numeroSerie, final Date validoDe, final Date validoHasta) {
		this.numeroSerie = numeroSerie;
		this.validoDe = validoDe;
		this.validoHasta =validoHasta;
	}
	
	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Date getValidoDe() {
		return validoDe;
	}

	public void setValidoDe(Date validoDe) {
		this.validoDe = validoDe;
	}

	public Date getValidoHasta() {
		return validoHasta;
	}

	public void setValidoHasta(Date validoHasta) {
		this.validoHasta = validoHasta;
	}

}
