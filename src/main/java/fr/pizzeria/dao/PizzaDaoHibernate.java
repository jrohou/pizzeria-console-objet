package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.pizzeria.console.CategoriePizza;
import fr.pizzeria.console.Pizza;

public class PizzaDaoHibernate implements IPizzaDao {
	
	/** EntityManagerFactory
	 *  entityManagerFactory
	 */
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizza_hiber");
	
	
	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#savePizza(fr.pizzeria.console.Pizza)
	 */
	@Override
	public void savePizza(Pizza pizza)  {
		EntityManager em = entityManagerFactory.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		em.persist(pizza);
		
		et.commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(int, java.lang.String, java.lang.String, fr.pizzeria.console.CategoriePizza, double)
	 */
	@Override
	public void updatePizza(int idpizza, String editcode, String editnom, CategoriePizza editcategorie, double editprix) {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		Pizza pizzaAModifier = em.find(Pizza.class, idpizza);
		pizzaAModifier.setCode(editcode);
		pizzaAModifier.setNom(editnom);
		pizzaAModifier.setCategorie(editcategorie);
		pizzaAModifier.setPrix(editprix);

		em.merge(pizzaAModifier);
		
		et.commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(int)
	 */
	@Override
	public void deletePizza(int idpizza) {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		Query query = em.createQuery("delete Pizza where id = :idpizza");
		query.setParameter("idpizza",idpizza);
		query.executeUpdate();
		
		et.commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizzas()
	 */
	@Override
	public List<Pizza> findAllPizzas() {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query et = em.createQuery("From Pizza");
		List <Pizza> list = et.getResultList();
		em.close();
		
		return list;
	}

}
