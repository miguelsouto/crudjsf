package com.crudjsf.domain.model;

public enum TipoAssociado {
	
	DEPENDENTE("Dependente"),
	RESPONSAVEL("Responsável");

	private final String label;
	
	public String getLabel() {
		return label;
	}
	
	private TipoAssociado(String label) {
		this.label = label;
	}	
}
