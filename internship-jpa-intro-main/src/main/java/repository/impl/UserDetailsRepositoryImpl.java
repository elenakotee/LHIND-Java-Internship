package repository.impl;

import configuration.EntityManagerConfiguration;
import model.entity.Booking;
import model.entity.Employee;
import model.entity.UserDetails;
import repository.UserDetailsRepository;
import util.Queries;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserDetailsRepositoryImpl implements UserDetailsRepository {

	private EntityManager entityManager = EntityManagerConfiguration.getEntityManager();

	@Override
	public List<UserDetails> findAll() {
		TypedQuery<UserDetails> result = entityManager.createQuery(Queries.FIND_ALL_USER_DETAILS, UserDetails.class);
		return result.getResultList();
	}

	@Override
	public Optional<UserDetails> findById(Integer id) {
		return Optional.ofNullable(entityManager.find(UserDetails.class, id));
	}

	@Override
	public void save(UserDetails user_details) {
		entityManager.getTransaction().begin();
		entityManager.persist(user_details);
		entityManager.getTransaction().commit();
	}
	@Override
	public List<UserDetails> findByFirstName(String first_name){
		TypedQuery<UserDetails> result = entityManager.createQuery(Queries.FIND_USER_DETAILS_BY_FIRST_NAME,
				UserDetails.class);
		result.setParameter("first_name", first_name);
		return result.getResultList();
	}

	@Override
	public List<UserDetails> findByLastName(String last_name){
		TypedQuery<UserDetails> result = entityManager.createQuery(Queries.FIND_USER_DETAILS_BY_LAST_NAME,
				UserDetails.class);
		result.setParameter("last_name", last_name);
		return result.getResultList();
	}

	@Override
	public List<UserDetails> findByEmail(String email){
		TypedQuery<UserDetails> result = entityManager.createQuery(Queries.FIND_USER_DETAILS_BY_EMAIL,
				UserDetails.class);
		result.setParameter("email", email);
		return result.getResultList();
	}

	@Override
	public List<UserDetails> findByPhoneNumber(String phone_number){
		TypedQuery<UserDetails> result = entityManager.createQuery(Queries.FIND_USER_DETAILS_BY_PHONE_NUMBER,
				UserDetails.class);
		result.setParameter("phone_number", phone_number);
		return result.getResultList();
	}

	@Override
	public Integer update(UserDetails userd) {
		entityManager.getTransaction().begin();
		UserDetails newud = entityManager.find(UserDetails.class, userd.getId());

		if(newud != null) {
			newud.setEmail(userd.getEmail());
			newud.setFirstName(userd.getFirstName());
			newud.setLastName(userd.getLastName());
			newud.setPhoneNumber(userd.getPhoneNumber());
			entityManager.getTransaction().commit();
			entityManager.close();
			return 1;
		}
		return 0;
	}

	@Override
	public void remove(Integer id) {
		entityManager.getTransaction().begin();
		UserDetails userd = entityManager.find(UserDetails.class, id);
		entityManager.remove(userd);
		entityManager.getTransaction().commit();
		entityManager.close();

	}


}
