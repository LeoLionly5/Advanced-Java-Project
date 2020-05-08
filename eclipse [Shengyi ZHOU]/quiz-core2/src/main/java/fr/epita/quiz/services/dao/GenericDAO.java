package fr.epita.quiz.services.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

public abstract class GenericDAO<T,I> {

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
		em.remove(em.merge(entity));
	}
	
//	public abstract List<T> search(T criteria);
//	public abstract T getById(I id);
	
	public abstract String getQuery();
	public abstract void setParameters(Map<String,Object> parameters, T criteria);
	
	public abstract String getSearchAllQuery();
	
	public List<T> search(T criteria){
		Query searchQuery = em.createQuery(getQuery());
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		setParameters(parameters, criteria);
		for (Map.Entry<String,Object> entry : parameters.entrySet() ) {
			searchQuery.setParameter(entry.getKey(), entry.getValue());
		}
		return searchQuery.getResultList();
	}
	
	public List<T> searchAll(){
		Query searchQuery = em.createQuery(getSearchAllQuery());
		return searchQuery.getResultList();
	}
	
//	public final T getById(I id) {
//		return em.find(getEntityClass(), id);
//	}
	
	public T getById(I id) {
		return em.find(getEntityClass(), id);
	}

	public abstract Class<T> getEntityClass();
}
