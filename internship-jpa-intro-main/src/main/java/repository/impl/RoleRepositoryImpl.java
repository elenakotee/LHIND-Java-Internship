package repository.impl;

import configuration.EntityManagerConfiguration;
import model.entity.Booking;
import model.entity.Employee;
import model.entity.Role;
import repository.RoleRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {

	private final EntityManager entityManager = EntityManagerConfiguration.getEntityManager();

	@Override
	public List<Role> findAll() {
		return null;
	}

	@Override
	public Optional<Role> findById(Integer id) {
		return Optional.ofNullable(entityManager.find(Role.class, id));
	}

	@Override
	public void save(Role role) {
		entityManager.getTransaction().begin();
		entityManager.persist(role);
		entityManager.getTransaction().commit();
	}

	@Override
	public Integer update(Role role) {
		entityManager.getTransaction().begin();
		Role newr = entityManager.find(Role.class, role.getId());

		if(newr != null) {
			newr.setRole(role.getRole());
			newr.setDescription(role.getDescription());
			entityManager.getTransaction().commit();
			entityManager.close();
			return 1;
		}
		return 0;
	}

	@Override
	public void remove(Integer id) {
		entityManager.getTransaction().begin();
		Role role = entityManager.find(Role.class, id);
		entityManager.remove(role);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
