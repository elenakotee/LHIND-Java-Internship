package repository.impl;

import configuration.EntityManagerConfiguration;
import model.entity.Booking;
import repository.Repository;
import util.Queries;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class BookingRepository implements Repository<Booking, Integer> {
	private EntityManager entityManager = EntityManagerConfiguration.getEntityManager();

	@Override
	public List<Booking> findAll() {
		TypedQuery<Booking> result =
				entityManager.createQuery(Queries.FIND_ALL_BOOKINGS, Booking.class);
		return result.getResultList();
	}

	@Override
	public Optional<Booking> findById(Integer id) {

		return Optional.ofNullable(entityManager.find(Booking.class, id));
	}

	@Override
	public void save(Booking booking) {
		entityManager.getTransaction().begin();
		entityManager.persist(booking);
		entityManager.getTransaction().commit();
	}

	@Override
	public Integer update(Booking booking) {
		entityManager.getTransaction().begin();
		Booking newbooking = entityManager.find(Booking.class, booking.getId());

		if(newbooking != null) {
			newbooking.setBookingNumber(booking.getBookingNumber());
			newbooking.setBookingEndDate(booking.getBookingEndDate());
			newbooking.setBookingStartDate(booking.getBookingStartDate());
			newbooking.setStatus(booking.getStatus());
			newbooking.setCost(booking.getCost());
			newbooking.setEmployee(booking.getEmployee());
			entityManager.getTransaction().commit();
			entityManager.close();
			return 1;
		}
		return 0;
	}

	@Override
	public void remove(Integer id) {
		entityManager.getTransaction().begin();
		Booking booking = entityManager.find(Booking.class, id);
		entityManager.remove(booking);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
