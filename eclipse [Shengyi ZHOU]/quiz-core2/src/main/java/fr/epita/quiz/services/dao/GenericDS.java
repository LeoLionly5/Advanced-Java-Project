package fr.epita.quiz.services.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.epita.quiz.datamodel.Question;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!No longer use!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public abstract class GenericDS<T,I> {

	@PersistenceContext
	protected EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public void create(T entity) {
		em.persist(entity);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void update(T entity) {
		em.merge(entity);
	}

	@Transactional(value = TxType.REQUIRED)
	public void delete(T entity) {
		em.remove(entity);
	}
	
	public abstract List<T> search(T criteria);
	public abstract T getById(I id);
	
	public abstract String getQuery();
	public abstract void setParameters(Map<String,Object> parameters, T entity);
	
	
}
