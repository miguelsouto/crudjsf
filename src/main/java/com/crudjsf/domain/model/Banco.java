package com.crudjsf.domain.model;

public enum Banco {
	CAIXA_ECONOMICA_FEDERAL("Caixa"),
	BANCO_BRASIL("Banco do Brasil"),
	SANTANDER("Santander"),
	BRADESCO("Bradesco"),
	ITAU("Itaú");

	private final String label;
	
	public String getLabel() {
		return label;
	}
	
	private Banco(String label) {
		this.label = label;
	}	
}
