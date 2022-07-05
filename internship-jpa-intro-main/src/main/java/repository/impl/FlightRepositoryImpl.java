package repository.impl;

import configuration.EntityManagerConfiguration;
import model.entity.Booking;
import model.entity.Employee;
import model.entity.Flight;
import repository.FlightRepository;
import util.Queries;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class FlightRepositoryImpl implements FlightRepository {
	private EntityManager entityManager = EntityManagerConfiguration.getEntityManager();

	@Override
	public List<Flight> findAll() {
		TypedQuery<Flight> result = entityManager.createQuery(Queries.FIND_ALL_FLIGHTS, Flight.class);
		return result.getResultList();
	}

	@Override
	public Optional<Flight> findById(Integer id) {

		return Optional.ofNullable(entityManager.find(Flight.class, id));
	}

	@Override
	public void save(Flight flight) {
		entityManager.getTransaction().begin();
		entityManager.persist(flight);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Flight> findByOrigin(String origin) {
		TypedQuery<Flight> result = entityManager.createQuery(Queries.FIND_FLIGHT_BY_ORIGIN, Flight.class);
		result.setParameter("origin", origin);
		return result.getResultList();
	}

	@Override
	public List<Flight> findByDestination(String destination) {
		TypedQuery<Flight> result = entityManager.createQuery(Queries.FIND_FLIGHT_BY_DESTINATION, Flight.class);
		result.setParameter("destination", destination);
		return result.getResultList();
	}

	@Override
	public List<Flight> findByAirline(String airline) {
		TypedQuery<Flight> result = entityManager.createQuery(Queries.FIND_FLIGHT_BY_AIRLINE, Flight.class);
		result.setParameter("airline", airline);
		return result.getResultList();
	}

	@Override
	public List<Flight> findByFlightNumber(String flight_number) {
		TypedQuery<Flight> result = entityManager.createQuery(Queries.FIND_FLIGHT_BY_FLIGHT_NUMBER, Flight.class);
		result.setParameter("flight_number", flight_number);
		return result.getResultList();
	}

	@Override
	public List<Flight> findByDepartureDate(String departure_date) {
		TypedQuery<Flight> result = entityManager.createQuery(Queries.FIND_FLIGHT_BY_DEPARTURE_DATE,
				Flight.class);
		result.setParameter("departure_date", departure_date);
		return result.getResultList();
	}

	@Override
	public List<Flight> findByArrivalDate(String arrival_date) {
		TypedQuery<Flight> result = entityManager.createQuery(Queries.FIND_FLIGHT_BY_ARRIVAL_DATE,
				Flight.class);
		result.setParameter("arrival_date", arrival_date);
		return result.getResultList();
	}

	@Override
	public Integer update(Flight flight) {
		entityManager.getTransaction().begin();
		Flight newf = entityManager.find(Flight.class, flight.getId());

		if(newf != null) {
			newf.setAirline(flight.getAirline());
			newf.setArrival_date(flight.getArrival_date());
			newf.setStatus(flight.getStatus());
			newf.setFlight_number(flight.getFlight_number());
			newf.setDeparture_date(flight.getDeparture_date());
			newf.setDestination(flight.getDestination());
			newf.setOrigin(flight.getOrigin());
			entityManager.getTransaction().commit();
			entityManager.close();
			return 1;
		}
		return 0;
	}

	@Override
	public void remove(Integer id) {
		entityManager.getTransaction().begin();
		Flight flight = entityManager.find(Flight.class, id);
		entityManager.remove(flight);
		entityManager.getTransaction().commit();
		entityManager.close();

	}



}
