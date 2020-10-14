package com.crudjsf.domain.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.crudjsf.domain.model.Associado;
import com.crudjsf.domain.model.HibernateSession;
import com.crudjsf.domain.model.TipoAssociado;

public class AssociadoDao {
	Session session;

    public AssociadoDao() {
        session = HibernateSession.getSessionFactory().openSession();
    }
    
    public void salvar(Associado associado){
        session.beginTransaction();
        session.saveOrUpdate(associado);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
	public List<Associado> listar(){    
		List<Associado> associado = session.createQuery("from Associado").getResultList();
        session.close();
        return associado;
    }
    
    public void remover(Associado associado){
        session.beginTransaction();
        session.delete(associado);
        session.getTransaction().commit();
        session.close();
    }
    
	public List<Associado> findResponsaveis(){
    	CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Associado> criteriaQuery = builder.createQuery(Associado.class);
        Root<Associado> associadoModel = criteriaQuery.from(Associado.class);
        criteriaQuery.select(associadoModel);
        criteriaQuery.where(builder.equal(associadoModel.get("tipoAssociado"), TipoAssociado.RESPONSAVEL));
        List<Associado> associadosResult = session.createQuery(criteriaQuery).list();
        return associadosResult;
    }
    
	public List<Associado> findByName(String nome){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Associado> criteriaQuery = builder.createQuery(Associado.class);
        Root<Associado> associadoModel = criteriaQuery.from(Associado.class);
        criteriaQuery.select(associadoModel);
        criteriaQuery.where(builder.equal(associadoModel.get("nome"), nome));
        List<Associado> associadosResult = session.createQuery(criteriaQuery).list();
        return associadosResult;
    }
    
    public Associado getById(Long id) {
        Associado associado =  (Associado) session.get(Associado.class, id);
        return associado;
    }
    
    public List<Associado> findDependentesByResponsavel(Associado responsavel) {
    	CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Associado> criteriaQuery = builder.createQuery(Associado.class);
        Root<Associado> associadoModel = criteriaQuery.from(Associado.class);
        criteriaQuery.select(associadoModel);
        criteriaQuery.where(builder.equal(associadoModel.get("responsavel"), responsavel));
        List<Associado> associadosResult = session.createQuery(criteriaQuery).list();
        return associadosResult;
    }
}
