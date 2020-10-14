package com.crudjsf.domain.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.crudjsf.domain.model.Associado;
import com.crudjsf.domain.model.HibernateSession;
import com.crudjsf.domain.model.Login;

public class LoginDao {
	Session session;

    public LoginDao() {
        session = HibernateSession.getSessionFactory().openSession();
    }
    
	public Login findByLogin(String login){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Login> criteriaQuery = builder.createQuery(Login.class);
        Root<Login> loginModel = criteriaQuery.from(Login.class);
        criteriaQuery.select(loginModel);
        criteriaQuery.where(builder.equal(loginModel.get("login"), login));
        Login loginResult = session.createQuery(criteriaQuery).getSingleResult();
        return loginResult;
    }
    
    public Associado getById(Long id) {
        Associado associado =  (Associado) session.get(Associado.class, id);
        return associado;
    }
    
    public void salvar(Login login){
        session.beginTransaction();
        session.saveOrUpdate(login);
        session.getTransaction().commit();
        session.close();
    }
}
