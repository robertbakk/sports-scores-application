package repository;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Base64;


public class UserRepo {

	private static String USER_BY_USERNAME_QUERY = "SELECT a from User a where a.username = :username";
	private static String UPDATE_USER_QUERY = "UPDATE User a set a.name = :name, a.mail = :mail, a.varsta = :age, a.password = :password WHERE a.id = :id";

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore");


	public void insertNewUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		user.setPassword(Base64.getEncoder().encodeToString((user.getId() + user.getPassword()).getBytes()));
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public User findUser(String id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		User user =	em.find(User.class,id);
		if (user != null)
			user.setPassword((new String(Base64.getDecoder().decode(user.getPassword()))).substring(user.getId().length()));
		em.close();
		return user;
	}

	public User findUserByUsername(String username) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query jpqlQuery = em.createQuery(USER_BY_USERNAME_QUERY, User.class).setParameter("username", username);
		if (jpqlQuery.getResultList().isEmpty()){
			em.close();
			return null;
		}
		User user = (User) jpqlQuery.getResultList().get(0);
		user.setPassword((new String(Base64.getDecoder().decode(user.getPassword()))).substring(user.getId().length()));
		em.close();
		return user;
	}

	public void updateUser(String id, String name, String mail, int age, String password) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery(UPDATE_USER_QUERY);
		q.setParameter("id", id);
		q.setParameter("name", name);
		q.setParameter("mail", mail);
		q.setParameter("age", age);
		q.setParameter("password", Base64.getEncoder().encodeToString((id+password).getBytes()));
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
}
