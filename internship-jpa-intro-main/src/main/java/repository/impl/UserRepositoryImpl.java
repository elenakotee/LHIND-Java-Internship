package repository.impl;

import configuration.EntityManagerConfiguration;
import model.entity.Booking;
import model.entity.User;
import model.entity.UserDetails;
import repository.UserRepository;
import util.Queries;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

	private EntityManager entityManager = EntityManagerConfiguration.getEntityManager();

	@Override
	public List<User> findAll() {
		TypedQuery<User> result = entityManager.createQuery(Queries.FIND_ALL_USERS, User.class);
		return result.getResultList();
	}

	@Override
	public Optional<User> findById(Integer id) {
		return Optional.ofNullable(entityManager.find(User.class, id));
	}

	@Override
	public void save(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<User> findByUsername(String username) {
		TypedQuery<User> result = entityManager.createQuery(Queries.FIND_USER_BY_USERNAME, User.class);
		result.setParameter("username", username);
		return result.getResultList();    }

	@Override
	public List<User> findByRole(String role) {
		TypedQuery<User> result = entityManager.createQuery(Queries.FIND_USER_BY_ROLE, User.class);
		result.setParameter("role", role);
		return result.getResultList();    }

	@Override
	public Integer update(User user) {
		entityManager.getTransaction().begin();
		User newu = entityManager.find(User.class, user.getId());

		if(newu != null) {
			newu.setRole(user.getRole());
			newu.setPassword(user.getPassword());
			newu.setUsername(user.getUsername());
			entityManager.getTransaction().commit();
			entityManager.close();
			return 1;
		}
		return 0;
	}

	@Override
	public void remove(Integer id) {
		entityManager.getTransaction().begin();
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
		entityManager.getTransaction().commit();
		entityManager.close();

	}
}
