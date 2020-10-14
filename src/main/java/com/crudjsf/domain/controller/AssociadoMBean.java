package com.crudjsf.domain.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.crudjsf.domain.model.Associado;
import com.crudjsf.domain.model.Banco;
import com.crudjsf.domain.model.ContaBancaria;
import com.crudjsf.domain.model.TipoAssociado;
import com.crudjsf.domain.repository.AssociadoDao;

@ManagedBean(name = "associadoMBean", eager = true)
@SessionScoped
public class AssociadoMBean implements Serializable{
	private static final long serialVersionUID = 1L;
    private Associado associado = new Associado();
    private ContaBancaria contaBancaria = new ContaBancaria();
        
    public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}
	
	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public void salvar(){
    	AssociadoDao associadoDao = new AssociadoDao();
    	checkBeforeInsert();
    	associadoDao.salvar(associado);
    	associadoDao = new AssociadoDao();
    	associado = new Associado();
    	contaBancaria = new ContaBancaria();
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Operação realizada com sucesso!"));
	}
	
	public void remover(Associado associado){
		String warnings = null;
		if(associado.getTipoAssociado().equals(TipoAssociado.RESPONSAVEL)) {
			warnings = checkBeforeRemove(associado);
		}
		
		if(warnings == null) {
	    	AssociadoDao associadoDao = new AssociadoDao();
	    	associadoDao.remover(associado);
	    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Associado removido com sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info:", warnings));
		}			
    }
	
	public String insert() {
		clean();
		return "/app/associadoAttributes?faces-redirect=true";
	}
	
	public String update() {
		prepararAssociado(associado);
		return "/app/associadoAttributes?faces-redirect=true";
	}
    
	public String voltar() {
		clean();
		return "/app/associado?faces-redirect=true";
	}
	
    public List<Associado> listar(){
    	AssociadoDao associadoDao = new AssociadoDao();
    	return associadoDao.listar();
    }
    
    public void clean() {
    	this.associado = new Associado();
    	this.contaBancaria = new ContaBancaria();
    }
    
    public List<Associado> findResponsaveis(){
    	AssociadoDao associadoDao = new AssociadoDao();
    	return associadoDao.findResponsaveis();
    }
    
    public List<Associado> complete(String nome){
    	AssociadoDao associadoDao = new AssociadoDao();
    	return associadoDao.findByName(nome);
    }
    
    public List<TipoAssociado> getTiposAssociados() {
        return Arrays.asList(TipoAssociado.values());
    }
    
    public List<Banco> getBancos() {
        return Arrays.asList(Banco.values());
    }
    
    public void prepararAssociado(Associado associado) {
    	if(associado.getContaBancaria() != null) {
    		this.setContaBancaria(associado.getContaBancaria());
    	}
    }
    
    public void checkBeforeInsert() {
    	if(associado.getTipoAssociado().equals(TipoAssociado.RESPONSAVEL)) {
    		associado.setContaBancaria(contaBancaria);
    	} else {
    		associado.setContaBancaria(null);
    	}
    }
    
    public String checkBeforeRemove(Associado associado) {
    	AssociadoDao associadoDao = new AssociadoDao();
    	List<Associado> dependentes = associadoDao.findDependentesByResponsavel(associado);
    	if(dependentes != null && !dependentes.isEmpty()) {
    		return "Antes de remover um responsável será preciso remover seus dependentes.";
    	}
    	return null;
    }
}
